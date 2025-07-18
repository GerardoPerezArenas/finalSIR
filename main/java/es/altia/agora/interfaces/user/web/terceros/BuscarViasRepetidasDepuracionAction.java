/* Generated by Together */
package es.altia.agora.interfaces.user.web.terceros;

import es.altia.agora.business.escritorio.UsuarioValueObject;
import es.altia.agora.business.terceros.mantenimiento.persistence.ViasManager;
import es.altia.agora.business.util.GeneralValueObject;
import es.altia.agora.interfaces.user.web.util.ActionSession;
import es.altia.common.service.config.Config;
import es.altia.common.service.config.ConfigServiceHelper;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class BuscarViasRepetidasDepuracionAction extends ActionSession {

    protected static Log m_Log =
            LogFactory.getLog(BuscarViasRepetidasDepuracionAction.class.getName());
    protected static Config m_ConfigTechnical = ConfigServiceHelper.getConfig("techserver");

    public ActionForward performSession(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws IOException, ServletException {

        m_Log.info("BuscarViasRepetidasDepuracionAction.perform");
        String[] params = null;
        HttpSession session = request.getSession();

        if (session.getAttribute("usuario") != null){
            UsuarioValueObject usuarioVO = (UsuarioValueObject)session.getAttribute("usuario");
            params = usuarioVO.getParamsCon();
        }

        DepuracionViasForm depuracionViasForm = (DepuracionViasForm) form;

        GeneralValueObject gVO = new GeneralValueObject();
        gVO.setAtributo("codProvincia", depuracionViasForm.getCodProvincia());
        gVO.setAtributo("codMunicipio", depuracionViasForm.getCodMunicipio());
        gVO.setAtributo("descVia", depuracionViasForm.getDescVia());

        Vector resultado = ViasManager.getInstance().getViasByDescAndProvinciaAndMunicipioGrouped(params, gVO);
        if (resultado.size() == 1) resultado = new Vector();
        Vector vias = new Vector();
        for (int i=0;i<resultado.size();i++) {
            gVO = (GeneralValueObject) resultado.get(i);
            ViaForm viaForm = new ViaForm();
            viaForm.setCodTipoVia((String)gVO.getAtributo("T_TVI.TVI_COD"));
            viaForm.setTipoVia((String)gVO.getAtributo("T_TVI.TVI_DES"));
            viaForm.setCodVia((String)gVO.getAtributo("T_VIA.VIA_COD"));
            viaForm.setDescVia((String)gVO.getAtributo("T_VIA.VIA_NOM"));
            viaForm.setCodProvincia((String)gVO.getAtributo("T_PRV.PRV_COD"));
            viaForm.setProvincia((String)gVO.getAtributo("T_PRV.PRV_NOM"));
            viaForm.setCodMunicipio((String)gVO.getAtributo("T_MUN.MUN_COD"));
            viaForm.setMunicipio((String)gVO.getAtributo("T_MUN.MUN_NOM"));

            vias.add(viaForm);
        }

        session.setAttribute("DepuracionVias.viasRepetidas", vias);
        return (mapping.findForward("default"));
    }
}