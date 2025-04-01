package org.fmm.acollyte.common.model;

public enum SolemnityEnum {
	SANTA_MARIA(1, "Santa María Madre de Dios"),
	EPIFANIA(2, "Epifanía del Señor"),
	SAN_JOSE(3, "San José Esposo de la Santísima Virgen María"),
	SANTIAGO(4, "Santiago, apóstol"),
	ASUNCION(5, "Asunción de la bienaventurada Virgen María"),
	SANTOS(6, "Todos los Santos"),
	INMACULADA(7, "Inmaculada Concepción de la bienaventurada Virgen María"),
	NATIVIDAD(8, "Natividad del Señor"),
	RAMOS(9, "Domingo de ramos en la pasión del Señor"),
	JUEVES_SANTO(10, "Jueves Santo en la Cena del Señor"),
	VIERNES_SANTO(11, "Viernes Santo en la Pasión del Señor"),
	VIGILIA_PASCUAL(12, "Vigilia Pascual en la Noche Santa"),
	DOMINGO_RESURRECCION(13, "Domingo de Pascua de la Resurrección del Señor");
    
    SolemnityEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    private int id;
    private String name;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
