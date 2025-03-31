# healenium-web-automation
Proyecto Java + Maven + Selenium con ejemplo de uso de Healenium

## Cómo empezar
### 0. Instalar Docker Desktop
### 1.Iniciar el backend de Healenium desde la carpeta infra

```cd infra```

```docker-compose up -d```

Verifique que las imágenes ```healenium/hlm-backend:3.4.1``` y ```postgres:11-alpine``` y ```healenium/hlm-selector-imitator:1.2``` estén en funcionamiento

### 2. Estructura del proyecto
```
|__infra
    |__db/sql
        |__init.sql
    |__docker-compose.yml
|__src/main/java/
|__src/test/java/
|__pom.xml
``` 
			   
### 3.Ejecutar prueba en terminal con maven

Si es la promera vez, usa ```mvn clean install```. Si no, usa ```mvn clean test```
 
### 4.Después de la ejecución de la prueba ver la URL del informe generado
Abrir `http://localhost:7878/healenium/report/`

El informe contiene solo los localizadores reparados con valores antiguos y nuevos y un botón que indica si la reparación fue exitosa para futuras correcciones del algoritmo

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/8h32s5mbynor70qnyp07.png)


![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/vj3p84e7kabdn1ifhlb1.png)


### 5. Ver el reporte de Allure
Después de la ejecución de la prueba usar comando ```allure serve target/allure-results```
Se abrirá un informe interactivo donde podrás ver cada caso de prueba, incluyendo las acciones realizadas y evidencias.

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/5yhtmwyzodtgbuerz11t.png)

### 6. Capturas de pantalla
También puede tomar capturas de pantalla para sus pruebas como se implementa aquí: BaseTest.java
```
  public byte[] screenshot() {
      return ((TakesScreenshot) driver.getDelegate()).getScreenshotAs(OutputType.BYTES);
  }
```
El screenshot se va a poder ver en el reporte de Allure, los screenshots de Healenium son automaticos.

### 7. Anotación @DisableHealing
Si no desea utilizar Healenium en algunos métodos, simplemente use la anotación @DisableHealing

### 8. Plugin Healenium para Intellij IDE
Para actualizar localizadores rotos puede utilizar el Plugin "Healenium" para Intellij IDE (https://plugins.jetbrains.com/plugin/14178-healenium).