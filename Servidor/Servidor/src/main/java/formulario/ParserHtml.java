package formulario;

import archivos.ManejadorArchivos;
import componente.Componente;

public class ParserHtml {

    private Formulario entrada;   
    private String rutaFormulario;

    private String style(String tema) {
        String auxTema = "";
        switch (tema) {
            case "WHITE":
                auxTema = temaClaro();
                break;
            case "DARK":
                auxTema = temaOscuro();
        }
        return "<style type=\"text/css\">\n"
                + auxTema
                + "    </style>";
    }

    private String temaClaro() {
        return "html {\n"
                + "\tbackground-color: #FBFBFE;\n"
                + "\tcolor: #697477\n"
                + "\t}\n"
                + "\tbody {\n"
                + "\n"
                + "\t\tmargin: 0 auto;\n"
                + "\t\tbackground-color: #FBFBFE;\n"
                + "\t\tpadding: 0 20px 20px 20px;\n"
                + "\t\tborder: 5px solid black;\n"
                + "\t}\n"
                + "\tdiv{\n"
                + "\t\tbackground-color: #d7d497;\n"
                + "\t\tcolor: #76afd7;\n"
                + "\t}\n"                
                + "\tlabel {\n"
                + "\t\tmargin: 0;\n"
                + "\t\tpadding: 20px 0;\n"
                + "\t\tcolor: #070707;\n"
                + "\t}\n";
    }

    private String temaOscuro() {
        return " html {\n"
                + "            background-color: #010101;\n"
                + "            color: #aaaaaa;\n"
                + "        }\n"
                + "\n"
                + "        body {\n"
                + "            color: #aaaaaa;\n"
                + "            margin: 0 auto;\n"
                + "            background-color: #aaaaaa;            \n"
                + "            padding: 0 20px 20px 20px;\n"
                + "            border: 5px solid black;\n"
                + "        }     \n"
                + "        div{\n"
                + "            background-color: #444c7b;\n"
                + "            color: #eaeff5;\n"
                + "        }\n"
                + "        select{\n"
                + "            color: #9ea0ab;\n"
                + "            background-color: #444c7b;\n"
                + "        }\n"
                + "        label {\n"
                + "            margin: 0;\n"
                + "            padding: 20px 0;\n"
                + "            color: #1083D6;\n"
                + "        }\n"
                + "        ";
    }

    private String inicioHtml() {
        return "<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>\n"
                + "<%@taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\n"
                + "<html>\n";
    }

    private String finHtml() {
        return "</html>";
    }

    private String inicioHead_titulo_finHead(String titulo) {
        return "\t<head>\n"
                + "\t\t<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6\" crossorigin=\"anonymous\">\n"
                + "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "\t\t<title>" + titulo + "</title>\n"
                + "\t</head>\n";
    }

    private String inicioBody() {
        return "\t<body>\n";
    }

    private String finBody() {
        return "\t</body>\n";
    }

    private String inicioForm() {
        return "\t\t<form>\n";
    }

    private String finForm() {
        return "\t\t</form>\n";
    }

    private String campoTexto(Componente entrada, String requerido) {
        return "\t\t\t<div class=\"form-floating\">\n"
                + "\t\t\t\t<textarea class=\"form-control\" placeholder=\"" + entrada.getTextoVisible() + "\" id=\"" + entrada.getNombreCampo() + "\"" + requerido + "</textarea>\n"
                + "\t\t\t\t<label for=\"floatingTextarea\">" + entrada.getTextoVisible() + "</label>\n"
                + "\t\t\t</div>\n";
    }

    private String areaTexto(Componente entrada, String requerido) {
        return "\t\t\t<div class=\"form-floating\">\n"
                + "\t\t\t\t<textarea class=\"form-control\" placeholder=\"" + entrada.getTextoVisible() + "\" id=\"" + entrada.getNombreCampo() + "\" style=\"height: " + convertidor(entrada.getFila(), 1) + "px;width: " + convertidor(entrada.getColumna(), 2) + "px\" " + requerido + "</textarea>\n"
                + "\t\t\t\t<label for=\"floatingTextarea2\">" + entrada.getTextoVisible() + "</label>\n"
                + "\t\t\t</div>\n";
    }
    private int convertidor(String entrada,int tipo){
        switch(tipo){
            case 1:               
                return (Integer.parseInt(entrada)*5)+300;
            case 2:
                return (Integer.parseInt(entrada)*5)+500;
        }
        return Integer.parseInt(entrada)+500;
    }
    private String checkBox(Componente entrada, boolean requeridoBoolean, String alineacion) {
        String requerido = "";
        if (requeridoBoolean) {
            requerido = "*";
        }
        String[] opciones = entrada.getOpciones().split("\\|");
        String retornoAux = "";
        retornoAux += "\t\t\t<div style=\"text-align: " + alineacion + "\"><label for=\"floatingInputValue\">" + entrada.getTextoVisible() + requerido + " :  </label></div>\n";
        retornoAux += "\t\t\t<div style=\"text-align: " + alineacion + "\" class=\"btn-group\" role=\"group\" aria-label=\"Basic checkbox toggle button group\">\n";
        for (String o : opciones) {
            retornoAux += "\t\t\t\t<input type=\"checkbox\" class=\"btn-check\" id=\"" + o + "\" autocomplete=\"off\">\n"
                    + "\t\t\t\t<label class=\"btn btn-outline-primary\" for=\"" + o + "\">" + o + "</label>\n"
                    + "\n";
        }
        retornoAux += "\t\t\t</div>\n";
        return retornoAux;
    }

    private String radioButton(Componente entrada, boolean requeridoBoolean, String alineacion) {
        String requerido = "";
        if (requeridoBoolean) {
            requerido = "*";
        }
        String[] opciones = entrada.getOpciones().split("\\|");
        String retornoAux = "";
        retornoAux += "\t\t\t<div style=\"text-align: " + alineacion + "\"><label for=\"floatingInputValue\">" + entrada.getTextoVisible() + requerido + " :  </label></div>\n";
        retornoAux += "\t\t\t<div style=\"text-align: " + alineacion + "\" class=\"btn-group\" role=\"group\" aria-label=\"Basic radio toggle button group\">\n";
        for (String o : opciones) {
            retornoAux += "\t\t\t<input type=\"radio\" class=\"btn-check\" name=\"" + entrada.getId() + "\" id=\"" + o + "\" autocomplete=\"off\" checked>\n"
                    + "\t\t\t<label class=\"btn btn-outline-primary\" for=\"" + o + "\"> " + o + " </label>\n";
        }
        retornoAux += "\t\t\t</div>";
        return retornoAux;
    }

    private String imagen(Componente entrada, String alineacion) {
        return "\t\t\t<img style=\"text-align: " + alineacion + "\" src=\"" + entrada.getUrl() + "\" class=\"rounded mx-auto d-block\" alt=\"La ruta de imagen es" + entrada.getUrl() + "\">\n";
    }

    private String comboBox(Componente entrada, boolean requeridoBoolean, String alineacion) {
        String requerido = "";
        if (requeridoBoolean) {
            requerido = "*";
        }
        String[] opciones = entrada.getOpciones().split("\\|");
        String retornoAux = "\t\t\t<div style=\"text-align: " + alineacion + "\"><label for=\"floatingInputValue\">" + entrada.getTextoVisible() + requerido + " :  </label></div>\n";
        retornoAux += "\t\t\t<select style=\"text-align: " + alineacion + "\" class=\"form-select\" aria-label=\"Default select example\">\n";
        for (String o : opciones) {
            retornoAux += "\t\t\t\t\t<option value=\"" + o + "\">" + o + "</option>\n";
        }
        retornoAux += "\t\t\t</select>\n";
        return retornoAux;
    }

    private String boton(Componente entrada) {
        return "\t\t\t<button type=\"button\" class=\"btn btn-primary\">" + entrada.getTextoVisible() + "</button>\n";
    }
    
    private String fichero(Componente entrada){
        return "<div class=\"input-group mb-3\">\n" +
"  <input type=\"file\" class=\"form-control\" id=\"inputGroupFile01\">\n" +
"</div>";
    }

    public ParserHtml(Formulario entrada) {
        this.entrada = entrada;
    }

    public void parser() {
        StringBuilder salida = new StringBuilder();
        salida.append(inicioHtml());

        salida.append(style(entrada.getTema()));

        salida.append(inicioHead_titulo_finHead(entrada.getTitulo()));

        salida.append(inicioBody());

        salida.append(inicioForm());

        for (Componente c : entrada.getComponentes()) {
            salida.append(agregarComponente(c));
        }

        salida.append(finForm());

        salida.append(finBody());

        salida.append(finHtml());
        this.rutaFormulario = ManejadorFormulario.rutaFormularioWeb + entrada.getId() + ".jsp";
        ManejadorArchivos.escribirArchivo(salida.toString(), rutaFormulario);
    }

    public String getRutaFormulario(String d) {
        return rutaFormulario;
    }

    private String agregarComponente(Componente componenteEntrada) {
        String aux, requeridoTexto = ">";
        boolean requerido = false;
        if (componenteEntrada.getRequerido() != null && componenteEntrada.getRequerido().equals("SI")) {
            requeridoTexto = "required>";
            requerido = true;
        }
        String alineacion = "IZQUIERDA";
        if (componenteEntrada.getAlineacion() != null) {
            alineacion = componenteEntrada.getAlineacion();
        }         
        switch (componenteEntrada.getClase()) {
            case "CAMPO_TEXTO":
                return campoTexto(componenteEntrada, requeridoTexto);
            case "AREA_TEXTO":
                return areaTexto(componenteEntrada, requeridoTexto);
            case "CHECKBOX":
                return checkBox(componenteEntrada, requerido, alineacionConvertidor(alineacion));
            case "RADIO":
                return radioButton(componenteEntrada, requerido, alineacionConvertidor(alineacion));
            case "IMAGEN":
                return imagen(componenteEntrada, alineacionConvertidor(alineacion));
            case "COMBO":
                return comboBox(componenteEntrada, requerido, alineacionConvertidor(alineacion));
            case "BOTON":
                return boton(componenteEntrada);
            case "FICHERO":
                return fichero(componenteEntrada);
        }
        return null;
    }

    private String alineacionConvertidor(String entrada) {
        switch (entrada) {
            case "CENTRO":
                return "center";
            case "IZQUIERDA":
                return "left";
            case "DERECHA":
                return "right";
            case "JUSTIFICAR":
                return "justify";
        }
        return null;
    }
}
