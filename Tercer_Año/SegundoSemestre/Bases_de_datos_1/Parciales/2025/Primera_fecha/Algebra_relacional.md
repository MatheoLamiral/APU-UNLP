LIBROS_ESPAÑOL ⇐ π<sub>#libro</sub>(σ<sub>idioma='Español'</sub>(LIBROS |X| LIBROIDIOMA |X| IDIOMA))  
LIBROS_NO_ESPAÑOL ⇐ π<sub>#libro</sub>(LIBRO) - LIBROS_ESPAÑOL  
EDITORIALES_NO_ESPAÑOL ⇐ π<sub>#editorial</sub>(LIBROS_NO_ESPAÑOL |X| EDITORIAL)  
π<sub>nombre</sub>((π<sub>#editorial</sub>(EDITORIAL) - EDITORIALES_NO_ESPAÑOL) |X| EDITORIAL) 