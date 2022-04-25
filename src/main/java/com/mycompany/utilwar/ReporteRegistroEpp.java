package com.mycompany.utilwar;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ReporteRegistroEpp {
    
    public void getReporte(String ruta, Integer codOperario, Integer codRegistro) throws ClassNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        System.out.println("ReporteRegistroEpp.getReporte 1: " + ruta);
        Connection conexion;
        System.out.println("ReporteRegistroEpp.getReporte 2: " + codOperario);
        Class.forName("org.postgresql.Driver").newInstance();
        System.out.println("ReporteRegistroEpp.getReporte 3: " + codRegistro);
        //Local Host
        conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/controlepps", "postgres", "1234");
        //Heroku Host
//        conexion = DriverManager.getConnection("jdbc:postgresql://ec2-54-225-182-108.compute-1.amazonaws.com:5432/dlq9dtirsjv9t?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "alwlraskjvbcam", "e6e23795116d68b54cf64455b32d80c434aa33b3270941402d18153b6ab0c279");
        System.out.println("ReporteRegistroEpp.getReporte 4: " );

        //Se definen los parametros que el reporte necesita
        Map parameter = new HashMap();
        parameter.put("codOperario", codOperario);
        parameter.put("codRegistro", codRegistro);
        

        try {
            File file = new File(ruta);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-Type", "application/pdf");
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);
            

            JRExporter jrExporter = null;
            jrExporter = new JRPdfExporter();
            jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
            
            if (jrExporter != null) {
                try {
                    jrExporter.exportReport();
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
