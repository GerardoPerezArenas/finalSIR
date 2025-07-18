/* Generated by Together */
package es.altia.agora.interfaces.user.web.administracion;

import es.altia.agora.business.escritorio.UsuarioValueObject;
import es.altia.agora.business.administracion.persistence.AutorizacionesExternasManager;
import es.altia.agora.business.administracion.mantenimiento.persistence.AplicacionesManager;
import es.altia.agora.business.administracion.mantenimiento.persistence.UsuariosGruposManager;
import es.altia.agora.business.administracion.AutorizacionesExternasValueObject;
import es.altia.agora.interfaces.user.web.helper.ActionHelper;
import es.altia.agora.interfaces.user.web.util.ActionSession;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class AutorizacionesExternasAction extends ActionSession {


    public ActionForward performSession(	ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws IOException, ServletException {

        m_Log.debug("perform");
        ActionHelper myActionHelper = new ActionHelper(getLocale(request), getResources(request));


        // Validaremos los parametros del request especificados
        HttpSession session = request.getSession();
        String opcion ="";

        if ((session.getAttribute("usuario") != null)) {
            UsuarioValueObject usuario = (UsuarioValueObject)session.getAttribute("usuario");
            String[] params = usuario.getParamsCon();
            int cod_dep;
            int cod_uni;
            int codOrg;
            int codEnt;
            cod_dep= usuario.getDepCod();
            cod_uni= usuario.getUnidadOrgCod();
            codOrg = usuario.getOrgCod();
            codEnt = usuario.getEntCod();
            String cOrg = Integer.toString(codOrg);
            String cEnt = Integer.toString(codEnt);
            String descOrg = usuario.getOrg();
            String descEnt = usuario.getEnt();


            // Si usuario en sesion es nulo --> error.

            ActionErrors errors = new ActionErrors();

            AutorizacionesExternasValueObject aeVO = new AutorizacionesExternasValueObject();
            AutorizacionesExternasForm aeForm = null;

            if (form == null) {
                m_Log.debug("Rellenamos el form de AutorizacionesExternas");
                form = new AutorizacionesExternasForm();
                if ("request".equals(mapping.getScope()))
                    request.setAttribute(mapping.getAttribute(), form);
                else
                    session.setAttribute(mapping.getAttribute(), form);
            }

            aeForm = (AutorizacionesExternasForm)form;

            opcion = request.getParameter("opcion");
            if (m_Log.isInfoEnabled()) m_Log.info("la opcion en el action es " + opcion);

            if (opcion.equals("inicio")){
                Vector listaComboAplicaciones = new Vector();
                listaComboAplicaciones = AplicacionesManager.getInstance().getListaAplicaciones(params);
                aeVO.setListaComboAplicaciones(listaComboAplicaciones);
                Vector listaUsuarios = new Vector();
                listaUsuarios = UsuariosGruposManager.getInstance().getUsuarios(params);
                aeVO.setListaUsuarios(listaUsuarios);
                aeForm.setAutorizacionesExternas(aeVO);
                opcion="inicio";
            } else if (opcion.equals("inicioAdmLocal")){
                Vector listaUsuarios = new Vector();
                listaUsuarios = UsuariosGruposManager.getInstance().getUsuariosLocal(cOrg,params);
                aeVO.setListaUsuarios(listaUsuarios);
                aeForm.setAutorizacionesExternas(aeVO);
                opcion="inicioAdmLocal";
            } else if(opcion.equals("buscarEntidades")){
                aeVO = aeForm.getAutorizacionesExternas();
                String codAplicacion = request.getParameter("codAplicacion");
                aeVO.setCodAplicacion(codAplicacion);
                Vector listaEntidades = new Vector();
                listaEntidades = AutorizacionesExternasManager.getInstance().getListaEntidadesAplicaciones(aeVO,params);
                aeVO.setListaEntidades(listaEntidades);
                aeVO.setListaAplicaciones(new Vector());
                aeVO.setListaEnt(new Vector());
                aeVO.setRespOpcion("buscarEntidades");
                aeForm.setAutorizacionesExternas(aeVO);
                opcion = "verOculto";
            } else if(opcion.equals("buscarAplicacionesUsuarios") || opcion.equals("buscarAplicacionesUsuariosAdmLocal")){
                aeVO = aeForm.getAutorizacionesExternas();
                String codUsuario = request.getParameter("codUsuario");
                aeVO.setCodUsuario(codUsuario);
                Vector listaAplicaciones = new Vector();
                listaAplicaciones = AutorizacionesExternasManager.getInstance().getListaAplicacionesUsuarios(aeVO,params);
                aeVO.setListaAplicaciones(listaAplicaciones);
                aeVO.setListaEntidades(new Vector());
                aeVO.setListaEnt(new Vector());
                aeVO.setRespOpcion("buscarAplicacionesUsuarios");
                aeForm.setAutorizacionesExternas(aeVO);
                if(opcion.equals("buscarAplicacionesUsuarios")) {
                    opcion = "verOculto";
                } else if(opcion.equals("buscarAplicacionesUsuariosAdmLocal")){
                    opcion = "verOcultoLocal";
                }
            } else if(opcion.equals("buscarEntidadesUsuarios")){
                aeVO = aeForm.getAutorizacionesExternas();
                String codUsuario = request.getParameter("codUsuario");
                aeVO.setCodUsuario(codUsuario);
                String codAplicacion = request.getParameter("codAplic");
                aeVO.setCodAplicacion(codAplicacion);
                Vector listaEnt = new Vector();
                listaEnt = AutorizacionesExternasManager.getInstance().getListaEntidadesUsuarios(aeVO,params);
                aeVO.setListaEnt(listaEnt);
                aeVO.setListaEntidades(new Vector());
                aeVO.setListaAplicaciones(new Vector());
                aeVO.setRespOpcion("buscarEntidadesUsuarios");
                aeForm.setAutorizacionesExternas(aeVO);
                opcion = "verOculto";
            } else if(opcion.equals("grabarListas")){
                aeVO = aeForm.getAutorizacionesExternas();
                String grabarPrimeraPest = request.getParameter("grabarPrimeraPest");
                String grabarSegundaPest = request.getParameter("grabarSegundaPest");
                String codAplicacion = request.getParameter("codAplicacion");
                aeVO.setCodAplicacion(codAplicacion);
                String lOrganizaciones = request.getParameter("lOrganizaciones");
                String lEntidades = request.getParameter("lEntidades");
                String lAplicaciones = request.getParameter("lAplicaciones");
                String lUsuarios = request.getParameter("lUsuarios");
                String lOrg = request.getParameter("lOrg");
                String lEnt = request.getParameter("lEnt");
                String lUsuar = request.getParameter("lUsuar");
                String lAplic = request.getParameter("lAplic");
                String lBaseDatos = request.getParameter("lBaseDatos");
                Vector listaOrganizaciones = listaTemasSeleccionados(lOrganizaciones);
                Vector listaEntidades = listaTemasSeleccionados(lEntidades);
                aeVO.setListaOrganizacionesUsuarios(listaOrganizaciones);
                aeVO.setListaEntidadesUsuarios(listaEntidades);
                aeVO.setListaAplicacionesPrimera(listaTemasSeleccionados(lAplicaciones));
                aeVO.setListaUsuariosPrimera(listaTemasSeleccionados(lUsuarios));
                aeVO.setListaOrganizacionesUsuariosSegunda(listaTemasSeleccionados(lOrg));
                aeVO.setListaEntidadesUsuariosSegunda(listaTemasSeleccionados(lEnt));
                aeVO.setListaUsuariosSegunda(listaTemasSeleccionados(lUsuar));
                aeVO.setListaAplicacionesUsuariosSegunda(listaTemasSeleccionados(lAplic));
                Vector listaBaseDatos = new Vector();
                if(lBaseDatos != null && !"".equals(lBaseDatos) && !"null".equals(lBaseDatos)) {
                    listaBaseDatos = listaTemasSeleccionados(lBaseDatos);
                }
                if(m_Log.isDebugEnabled()) m_Log.debug("la primera pesta�a es : " + grabarPrimeraPest);
                if(m_Log.isDebugEnabled()) m_Log.debug("la segunda pesta�a es : " + grabarSegundaPest);
                int resultado = AutorizacionesExternasManager.getInstance().grabarListas(aeVO,grabarPrimeraPest,grabarSegundaPest,params);
                if(m_Log.isDebugEnabled()) m_Log.debug("el resultado es : " + resultado);
                if(resultado>0) {
                    for(int i=0;i<listaBaseDatos.size();i++) {
                        String codEntidad = (String) listaEntidades.elementAt(i);
                        String codOrganizacion = (String) listaOrganizaciones.elementAt(i);
                        String baseDeDatos = (String) listaBaseDatos.elementAt(i);
                        AutorizacionesExternasManager.getInstance().grabarNombreBD(codAplicacion,codEntidad,codOrganizacion,baseDeDatos,params);
                    }
                    aeVO.setRespOpcion("grabarListas");
                    aeForm.setAutorizacionesExternas(aeVO);
                    opcion = "verOculto";
                }
            } else if(opcion.equals("grabarListasAdmLocal")){
                aeVO = aeForm.getAutorizacionesExternas();
                String codAplicacion = request.getParameter("codAplicacion");
                aeVO.setCodAplicacion(codAplicacion);
                String lAplicaciones = request.getParameter("lAplicaciones");
                String lUsuarios = request.getParameter("lUsuarios");
                String lOrg = request.getParameter("lOrg");
                String lEnt = request.getParameter("lEnt");
                String lUsuar = request.getParameter("lUsuar");
                String lAplic = request.getParameter("lAplic");
                String lBaseDatos = request.getParameter("lBaseDatos");
                aeVO.setListaAplicacionesPrimera(listaTemasSeleccionados(lAplicaciones));
                aeVO.setListaUsuariosPrimera(listaTemasSeleccionados(lUsuarios));
                aeVO.setListaOrganizacionesUsuariosSegunda(listaTemasSeleccionados(lOrg));
                aeVO.setListaEntidadesUsuariosSegunda(listaTemasSeleccionados(lEnt));
                aeVO.setListaUsuariosSegunda(listaTemasSeleccionados(lUsuar));
                aeVO.setListaAplicacionesUsuariosSegunda(listaTemasSeleccionados(lAplic));
                int resultado = AutorizacionesExternasManager.getInstance().grabarListasLocal(aeVO,params);
                if(m_Log.isDebugEnabled()) m_Log.debug("el resultado es : " + resultado);
                if(resultado>0) {
                    aeVO.setRespOpcion("grabarListas");
                    aeForm.setAutorizacionesExternas(aeVO);
                    opcion = "verOculto";
                }
            } else if("inicioDatosEjercicio".equals(opcion)) {
                opcion = "inicioDatosEjercicio";
            }
        } else { // No hay usuario.
            if(m_Log.isDebugEnabled()) m_Log.debug("MantAnotacionRegistroAction --> no hay usuario");
            opcion = "no_usuario";
        }

        /* Redirigimos al JSP de salida*/
        return (mapping.findForward(opcion));

    }

    private Vector listaTemasSeleccionados(String listTemasSelecc) {
        Vector lista = new Vector();
        int index = 0;
        int start = 0;
        String pattern = "��";
        if (listTemasSelecc!=null) {
            String aux = listTemasSelecc;
            while ((index = aux.indexOf(pattern))!=-1) {
                String valor = listTemasSelecc.substring(start, start+index);
                lista.addElement(valor);
                start = start + index + pattern.length();
                aux = listTemasSelecc.substring(start);
                if(m_Log.isDebugEnabled()) m_Log.debug("--> " + valor);
            }
        }
        return lista;
    }

}