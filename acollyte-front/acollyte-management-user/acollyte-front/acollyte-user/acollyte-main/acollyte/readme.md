Las propiedades para Actuator deben ir en formato properties

management:
    endpoint:
        metrics:
            enabled: true
    endpoints:
        web:
            exposure:
                include: \*
        prometheus:
            enabled: true
    metrics:
        export:
            prometheus:
                enabled: true

management.endpoints.web.exposure.include:



Metrics:
names:
  - hikaricp.connections
  - hikaricp.connections.acquire
  - hikaricp.connections.active
  - hikaricp.connections.creation
  - hikaricp.connections.idle
  - hikaricp.connections.max
  - hikaricp.connections.min
  - hikaricp.connections.pending
  - hikaricp.connections.timeout
  - hikaricp.connections.usage
  - http.server.requests
  - jdbc.connections.active
  - jdbc.connections.idle
  - jdbc.connections.max
  - jdbc.connections.min
  - jvm.buffer.count
  - jvm.buffer.memory.used
  - jvm.buffer.total.capacity
  - jvm.classes.loaded
  - jvm.classes.unloaded
  - jvm.gc.live.data.size
  - jvm.gc.max.data.size
  - jvm.gc.memory.allocated
  - jvm.gc.memory.promoted
  - jvm.gc.pause
  - jvm.memory.committed
  - jvm.memory.max
  - jvm.memory.used
  - jvm.threads.daemon
  - jvm.threads.live
  - jvm.threads.peak
  - jvm.threads.states
  - logback.events
  - process.cpu.usage
  - process.start.time
  - process.uptime
  - system.cpu.count
  - system.cpu.usage
  - tomcat.sessions.active.current
  - tomcat.sessions.active.max
  - tomcat.sessions.alive.max
  - tomcat.sessions.created
  - tomcat.sessions.expired
  - tomcat.sessions.rejected
