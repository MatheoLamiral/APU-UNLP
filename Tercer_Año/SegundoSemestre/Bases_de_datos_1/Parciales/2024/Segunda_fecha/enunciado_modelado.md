Una *vinoteca* quiere modelar una base de datos para almacenar los productos que ofrece y las *muestras* a realizar sobre esos productos.

De cada *vino* que la vinoteca ofrece se sabe su *nombre*, *bodega* y los distintos *varietales* (que son tipos de uva por ejemplo Malbec, Merlot, Moscatel y Tempranillo entre otras) que componen el vino, además del *porcentaje* de dicho varietal que lo compone. Por ejemplo, un vino puede estar compuesto por un 20% Malbec y un 30% Merlot. Si bien cada vino puede tener varios varietales, cada uno de ellos puede aparecer una única vez por vino.

Adicionalmente, los diferentes *varietales* se cultivan en *viñedos*. Un *viñedo* puede tener diferentes varietales en sus tierras así como un *varietal* puede ser objeto de cultivo de diversos viñedos. A partir de lo anterior, se debe saber de cuál de los *viñedos* fue extraído el varietal que compone cada vino.
De cada *varietal* se sabe su *nombre* y su *tipo*. De los *viñedos* se sabe su *nombre*, *dirección* y *altura*.

La vinoteca organiza diferentes *eventos* para que *clientes* o *expertos* en vino puedan probar sus diferentes vinos. De cada *evento* se registra un *nombre*, *fecha* en la que se realiza y los *vinos* que se eligen para ser presentados en ese evento. Un *mismo vino* puede ser presentado en *diferentes eventos*.
De cada *experto* que haya probado un vino presentado en un evento se registra una *reseña* y el *puntaje* que éste le otorgó al vino (un valor de 0 a 100). De cada *experto* se sabe su *nombre*, *apellido*, *cuit*, *fecha de nacimiento*, *nacionalidad* y *experiencia*.

Es importante para la vinoteca registrar la opinión de los expertos que han probado y reseñado el vino presentado, información que servirá luego para poder *promocionar sus vinos*.