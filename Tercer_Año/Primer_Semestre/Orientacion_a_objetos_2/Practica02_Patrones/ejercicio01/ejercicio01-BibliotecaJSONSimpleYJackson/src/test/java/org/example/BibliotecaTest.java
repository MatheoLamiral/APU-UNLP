package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BibliotecaTest {
    private Biblioteca biblioteca;
    private Socio socio1;
    private Socio socio2;
    private VoorheesExporter exporter;

    @BeforeEach
    void setup() throws Exception {
        this.biblioteca = new Biblioteca();
        this.socio1 = new Socio("socio1", "mail1@gmail.com","1234-5" );
        this.biblioteca.agregarSocio(socio1);
        this.socio2 = new Socio("socio2", "mail2@gmail.com","6789-1" );
        this.biblioteca.agregarSocio(socio2);

    }


    @Test
    void testExportar() {
        this.exporter =  new VoorheesExporter();
        String separator = System.lineSeparator();
        String message = "["+ separator + "\t{" + separator
                + "\t\t\"nombre\": \"" + "socio1" + "\"," + separator
                + "\t\t\"email\": \"" + "mail1@gmail.com" + "\"," + separator
                + "\t\t\"legajo\": \"" + "1234-5" + "\"" + separator
                + "\t}," + separator
                + "\t{" + separator
                + "\t\t\"nombre\": \"" + "socio2" + "\"," + separator
                + "\t\t\"email\": \"" + "mail2@gmail.com" + "\"," + separator
                + "\t\t\"legajo\": \"" + "6789-1" + "\"" + separator
                + "\t}" + separator
                + "]";
        assertEquals(message,this.biblioteca.exportarSocios());
    }


    @Test
    void testExportarJackson() {
        this.exporter =  new JacksonAdapter();
        String separator = System.lineSeparator();
        String message = "["+ separator + "\t{" + separator
                + "\t\t\"nombre\": \"" + "socio1" + "\"," + separator
                + "\t\t\"email\": \"" + "mail1@gmail.com" + "\"," + separator
                + "\t\t\"legajo\": \"" + "1234-5" + "\"" + separator
                + "\t}," + separator
                + "\t{" + separator
                + "\t\t\"nombre\": \"" + "socio2" + "\"," + separator
                + "\t\t\"email\": \"" + "mail2@gmail.com" + "\"," + separator
                + "\t\t\"legajo\": \"" + "6789-1" + "\"" + separator
                + "\t}" + separator
                + "]";
        assertEquals(message,this.biblioteca.exportarSocios());
    }



    @Test
    void testExportarJSONSimple() {
        this.exporter =  new JSONSimpleAdapter();
        String separator = System.lineSeparator();
        String message = "["+ separator + "\t{" + separator
                + "\t\t\"nombre\": \"" + "socio1" + "\"," + separator
                + "\t\t\"email\": \"" + "mail1@gmail.com" + "\"," + separator
                + "\t\t\"legajo\": \"" + "1234-5" + "\"" + separator
                + "\t}," + separator
                + "\t{" + separator
                + "\t\t\"nombre\": \"" + "socio2" + "\"," + separator
                + "\t\t\"email\": \"" + "mail2@gmail.com" + "\"," + separator
                + "\t\t\"legajo\": \"" + "6789-1" + "\"" + separator
                + "\t}" + separator
                + "]";
        assertEquals(message,this.biblioteca.exportarSocios());
    }


}
