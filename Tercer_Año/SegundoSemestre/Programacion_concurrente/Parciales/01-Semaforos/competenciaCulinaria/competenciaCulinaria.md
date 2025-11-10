```
    sem barrera = 0;
    vec recetas [C];
    sem esperaReceta ([C] 0);
    sem mutex = 1;
    cola platos;
    vec puntajes([C] 0);
    sem esperaPuntaje([C]0);
    sem exhibir = 0;

    Process concursante [id: 1..C]
    {
        V(barrera); // avisa que llego 
        P(esperaReceta[id]); // espera la receta
        plato = //cocinarPlato(receta[id]);
        P(mutex);
        platos.push(plato, id); // exhibe el plato
        V(mutex);
        V(exhibir); // aviso al jurado que llegue
        P(esperaPuntaje[id]); // espero mi puntaje
        puntaje = puntajes[id]; // tomo mi puntaje
    }

    Process jurado
    {
        for i: 1..C{
            P(barrera); // espera a que lleguen C concursantes
        }
        receta = new Receta();
        for i: 1..C{
            recetas[id] = receta;
            V(esperaReceta[id])
        }
        for i:1..C{
            P(exhibir); // espero a que algun concursante exhiba
            P(mutex);
            (plato, idConcursante) = platos.pop(); // tomo la exhibicion
            V(mutex);
            puntaje = // generarPuntaje(plato);
            puntajes[idConcursante] = puntaje; // doy la devolucion
            V(esperaPuntaje[idConcursante]); // le aviso que ya está la devolucion
        }
    }
```