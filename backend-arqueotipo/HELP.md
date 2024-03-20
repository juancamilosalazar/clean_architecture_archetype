# Proyecto Backend Arqueotipo Implementando Clean Architecture

# Arquitectura

<img alt="Clean Architecture" height="100%" src="https://miro.medium.com/v2/resize:fit:720/format:webp/0*pUhK2tTX-OSKSKtN.jpg" width="100%"/>

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain
Este capa se debe mantener lo más limpio que se pueda, no depender de librerias externas

### Model
Es el modulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la logica y reglas del negocio mediante clases abstractas (interfaces, llamados puertos), modelos o entidades de dominio.
No confundir el termino entidad, con las entidades en JPA, la cual representa un tabla en una bd.

Al ser el núcleo de la arquitectura, este módulo no depende nada más que de sí mismo. Al tener que relacionarse con su entorno, se ve obligado a definir el comportamiento esperado de la infraestructura mediante 
interfaces y depender únicamente de estas. A esta abstracción le conoceremos como gateways (puertos) del dominio y son fundamentales para desacoplar la tecnología del dominio;

### Usecases

Este modulo perteneciente a la capa del dominio, implementa los casos de uso del sistema, define logica de la aplicacion y reacciona a las invocaciones desde el modulo de entry points por medio del puerto de los casos de usos (Interface), 
cumpliendo uno de los principios SOLID el cual es no depender de implementaciones si no de clases abstractas y además es el encargado de orquestar los flujos hacia el modulo de entities (Model).

## Infrastructure

### Driven Adapters

Los driven adapter perteneciente a la capa de infraestructura, representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

Si se requiere trabajar con nuevos modulos para alguna tecnologia diferente a jpa, considere usar el modulo new-module (cambiar el nombre de acuerdo a su uso) o creé uno copiando el empaquetado de new-module.
para configurar el modulo se debe modificar el pom principal del proyecto y agregar el nuevo modulo en el apartado de <module>

            <module>infrastructure/driven-adapters/new-module</module>

en el pom del modulo application agrege la dependencia del modulo, el nombre del paquete principal hace referencia al modulo, en el ejemplo es new-module

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este modulo es el capa más externa de la arquitectura, es el encargado de ensamblar los distintos modulos, resolver las dependencias y 
crear los beans de los casos de use (UseCases) o configuraciones adicionales. 
Además inicia la aplicación (es el unico modulo del proyecto donde encontraremos la función public static void main(String[] args).



