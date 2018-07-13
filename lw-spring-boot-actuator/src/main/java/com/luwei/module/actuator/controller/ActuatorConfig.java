package com.luwei.module.actuator.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jdq
 * @date 2017/12/25 16:29
 */
@ConfigurationProperties(prefix = "endpoints")
@Configuration
public class ActuatorConfig {

    private Actuator actuator;

    private class Actuator {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Autoconfig autoconfig;

    private class Autoconfig {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Beans beans;

    private class Beans {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Configprops configprops;

    private class Configprops {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }


    private Dump dump;

    private class Dump {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Health health;

    private class Health {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Info info;

    private class Info {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Metrics metrics;

    private class Metrics {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Mappings mappings;

    private class Mappings {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Shutdown shutdown;

    private class Shutdown {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Trace trace;

    private class Trace {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Dnv env;

    private class Dnv {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Auditevents auditevents;

    private class Auditevents {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Hapdump heapdump;

    private class Hapdump {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Loggers loggers;

    private class Loggers {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Logfile logfile;

    private class Logfile {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Jolokia jolokia;

    private class Jolokia {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Docs docs;

    private class Docs {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Flyway flyway;

    private class Flyway {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }

    private Liquibase liquibase;

    private class Liquibase {
        private Boolean enabled = true;
        private Boolean sensitive = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getSensitive() {
            return sensitive;
        }

        public void setSensitive(Boolean sensitive) {
            this.sensitive = sensitive;
        }
    }


    public Actuator getActuator() {
        return actuator;
    }

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }

    public Autoconfig getAutoconfig() {
        return autoconfig;
    }

    public void setAutoconfig(Autoconfig autoconfig) {
        this.autoconfig = autoconfig;
    }

    public Beans getBeans() {
        return beans;
    }

    public void setBeans(Beans beans) {
        this.beans = beans;
    }

    public Configprops getConfigprops() {
        return configprops;
    }

    public void setConfigprops(Configprops configprops) {
        this.configprops = configprops;
    }

    public Dump getDump() {
        return dump;
    }

    public void setDump(Dump dump) {
        this.dump = dump;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public Mappings getMappings() {
        return mappings;
    }

    public void setMappings(Mappings mappings) {
        this.mappings = mappings;
    }

    public Shutdown getShutdown() {
        return shutdown;
    }

    public void setShutdown(Shutdown shutdown) {
        this.shutdown = shutdown;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public Dnv getEnv() {
        return env;
    }

    public void setEnv(Dnv env) {
        this.env = env;
    }

    public Auditevents getAuditevents() {
        return auditevents;
    }

    public void setAuditevents(Auditevents auditevents) {
        this.auditevents = auditevents;
    }

    public Hapdump getHeapdump() {
        return heapdump;
    }

    public void setHeapdump(Hapdump heapdump) {
        this.heapdump = heapdump;
    }

    public Loggers getLoggers() {
        return loggers;
    }

    public void setLoggers(Loggers loggers) {
        this.loggers = loggers;
    }

    public Logfile getLogfile() {
        return logfile;
    }

    public void setLogfile(Logfile logfile) {
        this.logfile = logfile;
    }

    public Jolokia getJolokia() {
        return jolokia;
    }

    public void setJolokia(Jolokia jolokia) {
        this.jolokia = jolokia;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    public Flyway getFlyway() {
        return flyway;
    }

    public void setFlyway(Flyway flyway) {
        this.flyway = flyway;
    }

    public Liquibase getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(Liquibase liquibase) {
        this.liquibase = liquibase;
    }
}
