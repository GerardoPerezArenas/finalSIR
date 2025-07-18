/**
 * VolanteEResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.altia.agora.webservice.tercero.servicios.iasoft.cliente;

public class VolanteEResponse  implements java.io.Serializable {
    private java.lang.String volanteEResult;

    public VolanteEResponse() {
    }

    public VolanteEResponse(
           java.lang.String volanteEResult) {
           this.volanteEResult = volanteEResult;
    }


    /**
     * Gets the volanteEResult value for this VolanteEResponse.
     * 
     * @return volanteEResult
     */
    public java.lang.String getVolanteEResult() {
        return volanteEResult;
    }


    /**
     * Sets the volanteEResult value for this VolanteEResponse.
     * 
     * @param volanteEResult
     */
    public void setVolanteEResult(java.lang.String volanteEResult) {
        this.volanteEResult = volanteEResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VolanteEResponse)) return false;
        VolanteEResponse other = (VolanteEResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.volanteEResult==null && other.getVolanteEResult()==null) || 
             (this.volanteEResult!=null &&
              this.volanteEResult.equals(other.getVolanteEResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getVolanteEResult() != null) {
            _hashCode += getVolanteEResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VolanteEResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.red.es/padron", ">VolanteEResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volanteEResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.red.es/padron", "VolanteEResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
