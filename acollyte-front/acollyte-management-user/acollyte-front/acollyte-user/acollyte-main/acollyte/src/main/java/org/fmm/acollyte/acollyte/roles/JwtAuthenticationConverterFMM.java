package org.fmm.acollyte.acollyte.roles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.fmm.acollyte.common.model.AppPermission;
import org.fmm.acollyte.common.model.AppRole;
import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.repository.AppPermissionRepository;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.fmm.common.oauth2.adhoc.model.entity.SocialUser;
import org.fmm.common.oauth2.adhoc.model.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
/**
 * Copia de org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
 * @author felix
 *
 */
@Component
public class JwtAuthenticationConverterFMM implements Converter<Jwt, AbstractAuthenticationToken> {

	private String principalClaimName = JwtClaimNames.SUB;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AppPermissionRepository permissionRepository;
	
	@Autowired
	private SocialUserRepository socialUserRepository;
	
	@Override
	public final AbstractAuthenticationToken convert(Jwt jwt) {
		Collection<GrantedAuthority> authorities = null;
		Optional<SocialUser> socialUser = null;
		Person p = null;
		SocialUser su = null;
		String principalClaimValue = null;
		// Hay que buscar el user en la bbdd
		// Buscar su persona (que es el user en la app)
		// Buscar sus roles
		socialUser = socialUserRepository.findUserByProviderId(jwt.getSubject());
		// Si la encuentra bien.
		// Si no la encuentra hay que registrarla y luego devolverle un: "Su solicitud está en espera"
		// Esto es porque el token pertenece a un usuario autenticado por Google, pero no ha sido autorizado
		// 
		su = socialUser.get();
		p = personRepository.findPersonPermissionsBySocialUser(su);
		principalClaimValue = jwt.getClaimAsString(this.principalClaimName);
		authorities = new ArrayList<>();
		// Sería mejor lanzar excepción: usuario no registrado.
		if (p != null) {
			List<AppPermission> permisos = new ArrayList<>();
			for (AppRole appRole: p.getAppRoles()) {
				permisos.addAll(permissionRepository.listPermissionsByRole(appRole.getId()));
			}
			for (AppPermission permission : permisos) {
				authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
			}
		}
		return new JwtAuthenticationToken(jwt, authorities, principalClaimValue);
	}

	/**
	 * Sets the principal claim name. Defaults to {@link JwtClaimNames#SUB}.
	 * @param principalClaimName The principal claim name
	 * @since 5.4
	 */
	public void setPrincipalClaimName(String principalClaimName) {
		Assert.hasText(principalClaimName, "principalClaimName cannot be empty");
		this.principalClaimName = principalClaimName;
	}

}
