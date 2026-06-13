package org.tony;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Campania {
    //esto de json property sirve para mapear el campo del json a la variable. lo he hecho asi
    //porque daba algun problema al intentar reconocer las variables y las dejaba nulas.
    @JsonProperty("TITULO_CAMPANIA")
    private String TITULO_CAMPANIA;

    @JsonProperty("NIVEL_RECOMENDADO")
    private String NIVEL_RECOMENDADO;

    @JsonProperty("NUMERO_SESIONES")
    private String NUMERO_SESIONES;

    @JsonProperty("TONO_NARRATIVO")
    private String TONO_NARRATIVO;

    @JsonProperty("TIPO_DE_CAMPANIA")
    private String TIPO_DE_CAMPANIA;

    @JsonProperty("GANCHO_INICIAL")
    private String GANCHO_INICIAL;

    @JsonProperty("CONFLICTO_CENTRAL")
    private String CONFLICTO_CENTRAL;

    @JsonProperty("Sesiones")
    private Map<String,String> Sesiones;

    @JsonProperty("ELEMENTOS_VINCULADOS")
    private String ELEMENTOS_VINCULADOS;
    /*
    * NumeroSesion
    * Titulo
    * ResumenNarrativo
    * Objetivo Principal
    * Localizaciones
    * Npcs Importantes ( con algo de detalle cada uno )
    * Eventos clave
    * Posibles decisiones de los jugadores
    * Consecuencias
    * Lista de Enemigos posibles
    * Botín posible
    * */
    public Campania() {

    }
    public Campania(String TITULO_CAMPANIA, String NIVEL_RECOMENDADO, String NUMERO_SESIONES, String TONO_NARRATIVO, String TIPO_DE_CAMPANIA, String GANCHO_INICIAL, String CONFLICTO_CENTRAL, Map<String, String> sesiones, String ELEMENTOS_VINCULADOS) {
        this.TITULO_CAMPANIA = TITULO_CAMPANIA;
        this.NIVEL_RECOMENDADO = NIVEL_RECOMENDADO;
        this.NUMERO_SESIONES = NUMERO_SESIONES;
        this.TONO_NARRATIVO = TONO_NARRATIVO;
        this.TIPO_DE_CAMPANIA = TIPO_DE_CAMPANIA;
        this.GANCHO_INICIAL = GANCHO_INICIAL;
        this.CONFLICTO_CENTRAL = CONFLICTO_CENTRAL;
        Sesiones = sesiones;
        this.ELEMENTOS_VINCULADOS=ELEMENTOS_VINCULADOS;
    }


    @Override
    public String toString() {
        return "Campania{" +
                "TITULO_CAMPANIA='" + TITULO_CAMPANIA + '\'' +
                ", NIVEL_RECOMENDADO='" + NIVEL_RECOMENDADO + '\'' +
                ", NUMERO_SESIONES='" + NUMERO_SESIONES + '\'' +
                ", TONO_NARRATIVO='" + TONO_NARRATIVO + '\'' +
                ", TIPO_DE_CAMPANIA='" + TIPO_DE_CAMPANIA + '\'' +
                ", GANCHO_INICIAL='" + GANCHO_INICIAL + '\'' +
                ", CONFLICTO_CENTRAL='" + CONFLICTO_CENTRAL + '\'' +
                ", Sesiones=" + Sesiones +
                ", ELEMENTOS_VINCULADOS" +ELEMENTOS_VINCULADOS + '\'' +
                '}';
    }

    private String cleanField(String input) {
        if (input == null) return null;
        return input.replaceFirst("^:\\s*\n?", ""); // elimina ':' al inicio y un salto de línea
    }

    @JsonProperty("TITULO_CAMPANIA")
    public String getTITULO_CAMPANIA() {
        return TITULO_CAMPANIA;
    }

    @JsonProperty("TITULO_CAMPANIA")
    public void setTITULO_CAMPANIA(String TITULO_CAMPANIA) {
        this.TITULO_CAMPANIA = cleanField(TITULO_CAMPANIA);
    }

    @JsonProperty("NIVEL_RECOMENDADO")
    public String getNIVEL_RECOMENDADO() {
        return NIVEL_RECOMENDADO;
    }

    @JsonProperty("NIVEL_RECOMENDADO")
    public void setNIVEL_RECOMENDADO(String NIVEL_RECOMENDADO) {
        this.NIVEL_RECOMENDADO = cleanField(NIVEL_RECOMENDADO);
    }

    @JsonProperty("NUMERO_SESIONES")
    public String getNUMERO_SESIONES() {
        return NUMERO_SESIONES;
    }

    @JsonProperty("NUMERO_SESIONES")
    public void setNUMERO_SESIONES(String NUMERO_SESIONES) {
        this.NUMERO_SESIONES = cleanField(NUMERO_SESIONES);
    }

    @JsonProperty("TONO_NARRATIVO")
    public String getTONO_NARRATIVO() {
        return TONO_NARRATIVO;
    }

    @JsonProperty("TONO_NARRATIVO")
    public void setTONO_NARRATIVO(String TONO_NARRATIVO) {
        this.TONO_NARRATIVO = cleanField(TONO_NARRATIVO);
    }

    @JsonProperty("TIPO_DE_CAMPANIA")
    public String getTIPO_DE_CAMPANIA() {
        return TIPO_DE_CAMPANIA;
    }

    @JsonProperty("TIPO_DE_CAMPANIA")
    public void setTIPO_DE_CAMPANIA(String TIPO_DE_CAMPANIA) {
        this.TIPO_DE_CAMPANIA = cleanField(TIPO_DE_CAMPANIA);
    }

    @JsonProperty("GANCHO_INICIAL")
    public String getGANCHO_INICIAL() {
        return GANCHO_INICIAL;
    }

    @JsonProperty("GANCHO_INICIAL")
    public void setGANCHO_INICIAL(String GANCHO_INICIAL) {
        this.GANCHO_INICIAL = cleanField(GANCHO_INICIAL);
    }

    @JsonProperty("CONFLICTO_CENTRAL")
    public String getCONFLICTO_CENTRAL() {
        return CONFLICTO_CENTRAL;
    }

    @JsonProperty("CONFLICTO_CENTRAL")
    public void setCONFLICTO_CENTRAL(String CONFLICTO_CENTRAL) {
        this.CONFLICTO_CENTRAL = cleanField(CONFLICTO_CENTRAL);
    }
    @JsonProperty("Sesiones")
    public Map<String, String> getSesiones() {
        return Sesiones;
    }
    @JsonProperty("Sesiones")
    public void setSesiones(Map<String, String> sesiones) {
        Sesiones = sesiones;
    }

    @JsonProperty("ELEMENTOS_VINCULADOS")
    public String getELEMENTOS_VINCULADOS() {
        return ELEMENTOS_VINCULADOS;
    }

    @JsonProperty("ELEMENTOS_VINCULADOS")
    public void setELEMENTOS_VINCULADOS(String elementosVinculados) {
        this.ELEMENTOS_VINCULADOS = elementosVinculados;
    }
}
