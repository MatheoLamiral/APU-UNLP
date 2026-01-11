# Imagen oficial de Tomcat con JDK 17
FROM tomcat:10.1-jdk17
# Eliminar las aplicaciones web predeterminadas
RUN rm -rf /usr/local/tomcat/webapps/*
# Copiar el archivo WAR de la aplicación al directorio webapps de Tomcat como ROOT.war
COPY target/BlogDeMensajesJPA-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war