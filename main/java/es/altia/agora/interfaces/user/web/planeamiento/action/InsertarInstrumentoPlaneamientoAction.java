/* Generated by Together */
package es.altia.agora.interfaces.user.web.planeamiento.action;

import es.altia.agora.business.escritorio.UsuarioValueObject;
import es.altia.agora.business.planeamiento.RegistroValueObject;
import es.altia.agora.business.planeamiento.AnotacionRegistroValueObject;
import es.altia.agora.business.planeamiento.PromotorValueObject;
import es.altia.agora.business.planeamiento.persistence.RegistroPlaneamientoManager;
import es.altia.agora.interfaces.user.web.util.ActionSession;
import es.altia.agora.interfaces.user.web.planeamiento.form.InstrumentoPlaneamientoForm;
import es.altia.agora.interfaces.user.web.planeamiento.form.AnotacionForm;
import es.altia.agora.interfaces.user.web.planeamiento.form.PromotorForm;
import es.altia.common.service.config.Config;
import es.altia.common.service.config.ConfigServiceHelper;
import es.altia.util.exceptions.InternalErrorException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class InsertarInstrumentoPlaneamientoAction extends ActionSession {

    protected static Log m_Log =
            LogFactory.getLog(InsertarInstrumentoPlaneamientoAction.class.getName());
    protected static Config m_ConfigTechnical = ConfigServiceHelper.getConfig("techserver");

    public ActionForward performSession(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws IOException, ServletException {

        try {
            m_Log.info("InsertarInstrumentoPlaneamientoAction.perform");
            String[] params = null;
            HttpSession session = request.getSession();

            if (session.getAttribute("usuario") != null){
                UsuarioValueObject usuarioVO = (UsuarioValueObject)session.getAttribute("usuario");
                params = usuarioVO.getParamsCon();
            }

            InstrumentoPlaneamientoForm instrumentoForm = (InstrumentoPlaneamientoForm) form;
            InstrumentoPlaneamientoForm instrumentoSessionForm = (InstrumentoPlaneamientoForm)
                    session.getAttribute("InstrumentoPlaneamientoForm");
            Calendar fechaAlta = Calendar.getInstance();
            Calendar fechaAprobacion = Calendar.getInstance();
            Calendar fechaVigencia = Calendar.getInstance();
            Calendar fechaBaja = Calendar.getInstance();
            Calendar fechaPublicacion = Calendar.getInstance();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                String fecha = instrumentoForm.getFechaAprobacion();
                if ((fecha==null) || (fecha.equals(""))) {
                    fechaAprobacion = null;
                } else {
                    fechaAprobacion.setTime(format.parse(fecha));
                }
                fecha = instrumentoForm.getFechaVigencia();
                if ((fecha==null) || (fecha.equals(""))) {
                    fechaVigencia = null;
                } else {
                    fechaVigencia.setTime(format.parse(fecha));
                }
                fecha = instrumentoForm.getFechaBaja();
                if ((fecha==null) || (fecha.equals(""))) {
                    fechaBaja = null;
                } else {
                    fechaBaja.setTime(format.parse(fecha));
                }
                fecha = instrumentoForm.getFechaPublicacion();
                if ((fecha==null) || (fecha.equals(""))) {
                    fechaPublicacion = null;
                } else {
                    fechaPublicacion.setTime(format.parse(fecha));
                }
            } catch (Exception e) {//En principio nunca va a entrar
                e.printStackTrace();
                if (m_Log.isErrorEnabled()) m_Log.error(e.getMessage());
            }
            Calendar fechaYear = Calendar.getInstance();
            SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
            Integer numeroProcedimiento = null;
            if (instrumentoForm.getNumeroProcedimiento()!=null && !instrumentoForm.getNumeroProcedimiento().equals("")) {
                numeroProcedimiento = new Integer(instrumentoForm.getNumeroProcedimiento());
            }
            RegistroValueObject registroVO = new RegistroValueObject(
                    new Character(instrumentoForm.getTipoRegistro().charAt(0)),
                    instrumentoForm.getCodigoSubseccion(), instrumentoForm.getCodigoTipo(), null,
                    formatYear.format(fechaYear.getTime()),
                    instrumentoForm.getNumeroRegistro(), fechaAlta, fechaAprobacion, fechaVigencia, fechaBaja,
                    instrumentoForm.getCodigoProcedimiento(), numeroProcedimiento, instrumentoForm.getCodigoAmbito(),
                    instrumentoForm.getParcela(), new Character(instrumentoForm.getPromotor().charAt(0)),
                    instrumentoForm.getCodigoOrganoAprobacion(), null, null, null, null, null, null, fechaPublicacion,
                    instrumentoForm.getNumeroPublicacion(), instrumentoForm.getObservaciones(),
                    instrumentoForm.getArchivo());

            Iterator anotacionesIt = instrumentoSessionForm.getAnotaciones().iterator();
            Collection anotaciones = new ArrayList();
            AnotacionRegistroValueObject anotacionVO = null;
            AnotacionForm anotacionForm = null;
            Calendar fechaAnotacion = null;
            String comentario = "";
            while (anotacionesIt.hasNext()) {
                anotacionForm = (AnotacionForm) anotacionesIt.next();
                fechaAnotacion = Calendar.getInstance();
                try {
                    String fecha = anotacionForm.getFechaAnotacion();
                    if ((fecha==null) || (fecha.equals(""))) {
                        fechaAnotacion = null;
                    } else {
                        fechaAnotacion.setTime(format.parse(fecha));
                    }
                } catch (Exception e) {//En principio nunca va a entrar
                    e.printStackTrace();
                    if (m_Log.isErrorEnabled()) m_Log.error(e.getMessage());
                }
                comentario = anotacionForm.getComentarioAnotacion().replaceAll("@intro@", "\r\n");
                anotacionVO = new AnotacionRegistroValueObject(new Character(instrumentoForm.getTipoRegistro().charAt(0)),
                        instrumentoForm.getCodigoSubseccion(), null,
                        formatYear.format(fechaYear.getTime()), anotacionForm.getNumeroAnotacion(), fechaAnotacion,
                        comentario);
                anotaciones.add(anotacionVO);
            }

            Collection promotores = new ArrayList();
            String codigoPromotor;

            /*Si el promotor es alguno de los contenidos en Planeamiento.Promotores.NoPromotores no se deberian insertar
            * lo que venga en getCodigosPromotor. Ej: Ayuntamiento de Ecija
            */
            String promotor = instrumentoForm.getPromotor();
            boolean noPromotores = false;
            Iterator noPromotoresColIt =
                    m_ConfigTechnical.getCollection("Planeamiento.Promotores.NoPromotores").iterator();
            while (noPromotoresColIt.hasNext()) {
                codigoPromotor = (String) noPromotoresColIt.next();
                if (promotor.equals(codigoPromotor)) {
                    noPromotores = true;
                    break;
                }
            }

            if (!noPromotores) {
                if (instrumentoSessionForm.getCodigosPromotor().isEmpty()) {
                    return (mapping.findForward("promotoresNoExisten"));
                }
                Iterator promotoresIt = instrumentoSessionForm.getCodigosPromotor().iterator();
                PromotorValueObject promotorVO = null;
                PromotorForm promotorForm = null;
                while (promotoresIt.hasNext()) {
                    promotorForm = (PromotorForm) promotoresIt.next();
                    promotorVO = new PromotorValueObject(new Character(instrumentoForm.getTipoRegistro().charAt(0)),
                            instrumentoForm.getCodigoSubseccion(), null,
                            formatYear.format(fechaYear.getTime()), new Integer(promotorForm.getCodigo()));
                    promotores.add(promotorVO);
                }
            }

            Integer numero = RegistroPlaneamientoManager.getInstance().create(registroVO, anotaciones,
                    promotores, params);
            instrumentoSessionForm.setNumero(numero.toString());
            instrumentoSessionForm.setNumeroRegistro(formatYear.format(fechaYear.getTime()) + "/" + numero.toString());
            instrumentoSessionForm.setAnho(formatYear.format(fechaYear.getTime()));
            instrumentoSessionForm.setTipoRegistro(instrumentoForm.getTipoRegistro());
            instrumentoSessionForm.setCodigoSubseccion(instrumentoForm.getCodigoSubseccion());
            instrumentoSessionForm.setCodigoTipo(instrumentoForm.getCodigoTipo());
            instrumentoSessionForm.setFechaAprobacion(instrumentoForm.getFechaAprobacion());
            instrumentoSessionForm.setFechaVigencia(instrumentoForm.getFechaVigencia());
            instrumentoSessionForm.setFechaBaja(instrumentoForm.getFechaBaja());
            instrumentoSessionForm.setCodigoProcedimiento(instrumentoForm.getCodigoProcedimiento());
            instrumentoSessionForm.setNumeroProcedimiento(instrumentoForm.getNumeroProcedimiento());
            instrumentoSessionForm.setCodigoAmbito(instrumentoForm.getCodigoAmbito());
            instrumentoSessionForm.setParcela(instrumentoForm.getParcela());
            instrumentoSessionForm.setPromotor(instrumentoForm.getPromotor());
            instrumentoSessionForm.setCodigoOrganoAprobacion(instrumentoForm.getCodigoOrganoAprobacion());
            instrumentoSessionForm.setFechaPublicacion(instrumentoForm.getFechaPublicacion());
            instrumentoSessionForm.setNumeroPublicacion(instrumentoForm.getNumeroPublicacion());
            instrumentoSessionForm.setObservaciones(instrumentoForm.getObservaciones());
            instrumentoSessionForm.setArchivo(instrumentoForm.getArchivo());

            session.setAttribute("InstrumentoPlaneamientoForm", instrumentoSessionForm);

            Collection instrumentos = new ArrayList();
            instrumentos.add(instrumentoSessionForm);
            session.setAttribute("registrosPlaneamiento", instrumentos);
            request.setAttribute("registroActual", "1");

            return (mapping.findForward("default"));
        } catch  (InternalErrorException e) {
            return (mapping.findForward("errorInsercion"));
        }
    }
}