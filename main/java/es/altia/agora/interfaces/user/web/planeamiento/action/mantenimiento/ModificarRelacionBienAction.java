/* Generated by Together */
package es.altia.agora.interfaces.user.web.planeamiento.action.mantenimiento;

import es.altia.agora.business.escritorio.UsuarioValueObject;
import es.altia.agora.business.planeamiento.mantenimiento.persistence.MantRelacionBienManager;
import es.altia.agora.business.planeamiento.mantenimiento.MantRelacionBienValueObject;
import es.altia.agora.interfaces.user.web.util.ActionSession;
import es.altia.agora.interfaces.user.web.planeamiento.form.mantenimiento.MantenimientoRelacionesBienForm;
import es.altia.agora.interfaces.user.web.planeamiento.form.mantenimiento.MantenimientoRelacionBienForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class ModificarRelacionBienAction extends ActionSession {

    protected static Log m_Log =
            LogFactory.getLog(ModificarRelacionBienAction.class.getName());

    public ActionForward performSession(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws IOException, ServletException {

        m_Log.info("ModificarRelacionBienAction.perform");
        String[] params = null;
        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") != null){
            UsuarioValueObject usuarioVO = (UsuarioValueObject)session.getAttribute("usuario");
            params = usuarioVO.getParamsCon();
        }

        MantenimientoRelacionesBienForm relacionesBienForm = (MantenimientoRelacionesBienForm) form;
        MantRelacionBienValueObject relacionBienVO = new MantRelacionBienValueObject(relacionesBienForm.getCodigo(), relacionesBienForm.getDescripcion());
        MantRelacionBienManager.getInstance().modify(relacionBienVO, params);

        Collection relacionesBien = new ArrayList();
        MantenimientoRelacionBienForm relacionBienForm = null;
        Iterator relacionesBienIt = ((MantenimientoRelacionesBienForm)
                session.getAttribute("MantenimientoRelacionesBienForm")).getRelacionesBien().iterator();
        while (relacionesBienIt.hasNext()) {
            relacionBienForm = (MantenimientoRelacionBienForm) relacionesBienIt.next();
            if (relacionBienForm.getCodigo().equals(relacionesBienForm.getCodigo())) {
                relacionBienForm.setDescripcion(relacionesBienForm.getDescripcion());
            }
            relacionesBien.add(relacionBienForm);
        }
        
        relacionesBienForm.setRelacionesBien(relacionesBien);

        session.setAttribute("MantenimientoRelacionesBienForm", relacionesBienForm);
        return (mapping.findForward("default"));
    }
}