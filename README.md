# bpo_escritorio_admin
La versión del proyecto de 2019 daba errores de versión en el IDE Netbeans (se probó con varias versiones), por lo que se creó un nuevo proyecto con el código fuente y se añadieron las librerías.

- Se adicionó a la clase utiles la validacioón de las fechas en los componentes de tipo fecha si es distinta de null con una funcioón de validación implementada.

- En el manejo de las advertencias y condicionantes se implementó que al adicionar los elementos seleccionados no se repitieran entre los q ya estaban además de que se adicionó el caso por defecto para cuando no es elegida ninguna opción. 

- En todas las pantallas que incluyen fechas se incorporó luego de la validación de los campos, las respuestas de fecha incorrecta o campo requerido según la respuesta de la validación.

- En la pantalla de cargas se puso el Porcentaje participación por defecto en 100.

- En la pantalla principal se adicionó el manejo de los datos insertados de un titular para q al adicionarlo ya se escriban por defecto en la pantalla titular.

- En la pantalla procesarFinca al llenar el campo valor tasación, se copia automáticamente el dato en los campos valoración y valor hipotecario.

- En la pantalla procesarTasación el campo Empresa tasadora se cambió a tipo combo en vez de tipo texto, creando un listado con el nombre de las empresas para que una vez seleccionada la empresa, se cumplimenten automáticamente los campos Código tasación sociedad estadístico y fecha valoración estadística, según los datos registrados para cada empresa.
También se tuvo en cuenta que sino se adicionaron las advertencias y condicionantes se implementó el caso por defecto para cuando no es elegida ninguna opción. Ademas se implemento una funcion para construir transformar la fecha valoración estadística de la empresa tasadora obtenida en un formato válido para el componente fecha. 

- En la pantalla FormaTitular se implemento una funcion para llenar los datos de un titular capturados en la pantalla principal y llenar el formulario excepto nombre, apellidos y NIF/CIF titular.
