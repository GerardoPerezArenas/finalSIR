/* Generated by Together */

package es.altia.agora.business.administracion;

import java.io.Serializable;
import es.altia.technical.Messages;
import es.altia.technical.ValidationException;


public class AgendaValueObject implements Serializable {

    /** Construye una nueva Ocupacion por defecto. */
    public AgendaValueObject() {
        super();
    }

    /** Construye una nueva Ocupacion cuyo estado esta definido en los parametros */
    public AgendaValueObject(String hora, String personas_ir, String citas_ir, String personas_mmr, String citas_mmr, String validez) {
            this.hora = hora;
            this.personas_ir = personas_ir;
            this.citas_ir = citas_ir;
            this.personas_mmr = personas_mmr;
            this.citas_mmr = citas_mmr;
            this.valido = validez;
    }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getPersonas_ir() { return personas_ir; }
    public void setPersonas_ir(String personas_ir) { this.personas_ir = personas_ir; }

    public String getCitas_ir() { return citas_ir; }
    public void setCitas_ir(String citas_ir) { this.citas_ir = citas_ir; }

    public String getPersonas_mmr() { return personas_mmr; }
    public void setPersonas_mmr(String personas_mmr) { this.personas_mmr = personas_mmr; }

    public String getCitas_mmr() { return citas_mmr; }
    public void setCitas_mmr(String citas_mmr) { this.citas_mmr = citas_mmr; }

    public String getValido() { return valido; }
    public void setValido(String valido) { this.valido = valido; }



  //Inic Raquel
  //Se a�aden metodos para los campos de la tabla Gen_Agenda
    public String getCod_centro() {return this.cod_centro;}
    public void setCod_centro(String cod_centro) {this.cod_centro = cod_centro;}

    public String getCod_ubic() {return this.cod_ubic;}
    public void setCod_ubic(String cod_ubic) {this.cod_ubic = cod_ubic;}

    public String getAge_funcion () {return this.age_funcion;}
    public void setAge_funcion (String age_funcion) {this.age_funcion = age_funcion;}

    public String getHora_ini () {return this.hora_ini;}
    public void setHora_ini (String hora_ini) {this.hora_ini = hora_ini;}

    public String getHora_fin () {return this.hora_fin;}
    public void setHora_fin (String hora_fin) {this.hora_fin = hora_fin;}

    public int getNum_max () {return this.num_max;}
    public void setNum_max (int num_max) {this.num_max = num_max; }

    public int getNum_disp () {return this.num_disp;}
    public void setNum_disp (int num_disp) {this.num_disp = num_disp; }

  //Fin Raquel


    /**
     * Valida el estado de esta Ocupacion
     * Puede ser invocado desde la capa cliente o desde la capa de negocio
     * @exception ValidationException si el estado no es v�lido
     */
    public void validate() throws ValidationException {
        boolean correcto = true;
        Messages errors = new Messages();

        isValid = true;
    }

    /** Devuelve un booleano que representa si el estado de esta Demanda es v�lido. */
    public boolean IsValid() { return isValid; }

    private String hora;
    private String personas_ir;
  private String citas_ir;
    private String personas_mmr;
    private String citas_mmr;
    private String valido;

  //Inic Raquel
    String cod_centro = "";
    String cod_ubic = "";
    String age_funcion = "";
    String hora_ini = "";
    String hora_fin = "";
    int num_max = 0;
    int num_disp = 0;
  //Fin Raquel

    /** Variable booleana que indica si el estado de la instancia de Demanda es v�lido o no */
    private boolean isValid;
}
