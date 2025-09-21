```Java
    /**
    * Retorna los últimos N posts que no pertenecen al usuario user
    */
    public List<Post> ultimosPosts(Usuario user, int cantidad) {
            
        List<Post> postsOtrosUsuarios = new ArrayList<Post>();
        for (Post post : this.posts) {
            if (!post.getUsuario().equals(user)) {
                postsOtrosUsuarios.add(post);
            }
        }
            
        // ordena los posts por fecha
        for (int i = 0; i < postsOtrosUsuarios.size(); i++) {
            int masNuevo = i;
            for(int j= i +1; j < postsOtrosUsuarios.size(); j++) {
                if (postsOtrosUsuarios.get(j).getFecha().isAfter(
            postsOtrosUsuarios.get(masNuevo).getFecha())) {
                    masNuevo = j;
                }    
            }
            Post unPost = postsOtrosUsuarios.set(i,postsOtrosUsuarios.get(masNuevo));
            postsOtrosUsuarios.set(masNuevo, unPost);    
        }
            
        List<Post> ultimosPosts = new ArrayList<Post>();
        int index = 0;
        Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
        while (postIterator.hasNext() &&  index < cantidad) {
            ultimosPosts.add(postIterator.next());
        }
        return ultimosPosts;
    }
```
### Bad smell: Reinventando la rueda 
```Java      
    List<Post> postsOtrosUsuarios = new ArrayList<Post>();
    for (Post post : this.posts) {
        if (!post.getUsuario().equals(user)) {
            postsOtrosUsuarios.add(post);
        }
    }
```
1. Aplico replace loop with pipeline
2. Reemplazo la asignación de `postsOtrosUsuarios` por el nuevo stream
```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {

        List<Post> postOtrosUsuarios = this.posts.stream()
                                                 .filter(post-> !post.getUsuario().equals(user))
                                                 .collect(Collectors.toList());
        // ordena los posts por fecha
        for (int i = 0; i < postsOtrosUsuarios.size(); i++) {
            int masNuevo = i;
            for(int j= i +1; j < postsOtrosUsuarios.size(); j++) {
                if (postsOtrosUsuarios.get(j).getFecha().isAfter(
            postsOtrosUsuarios.get(masNuevo).getFecha())) {
                    masNuevo = j;
                }    
            }
            Post unPost = postsOtrosUsuarios.set(i,postsOtrosUsuarios.get(masNuevo));
            postsOtrosUsuarios.set(masNuevo, unPost);    
        }
            
        List<Post> ultimosPosts = new ArrayList<Post>();
        int index = 0;
        Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
        while (postIterator.hasNext() &&  index < cantidad) {
            ultimosPosts.add(postIterator.next());
        }
        return ultimosPosts;
    }
  ```
### Bad smell: Reinventando la rueda
```Java
    for (int i = 0; i < postsOtrosUsuarios.size(); i++) {
            int masNuevo = i;
            for(int j= i +1; j < postsOtrosUsuarios.size(); j++) {
                if (postsOtrosUsuarios.get(j).getFecha().isAfter(postsOtrosUsuarios.get(masNuevo).getFecha())) {
                    masNuevo = j;
                }    
            }
            Post unPost = postsOtrosUsuarios.set(i,postsOtrosUsuarios.get(masNuevo));
            postsOtrosUsuarios.set(masNuevo, unPost);    
    }
  ```
1. Aplico replace loop with pipeline
2. Creo la variable `List<Post> postOtrosUsuariosOrdenados` y le asigno el nuevo stream
```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {

        List<Post> postOtrosUsuarios = this.posts.stream()
                                                 .filter(post-> !post.getUsuario().equals(user))
                                                 .collect(Collectors.toList());

        List<Post> postOtrosUsuariosOrdenados = postOtrosUsuarios.stream()
                                                                 .sorted((post1, post2) -> post2.getFecha().compareTo(post1.getFecha()))
                                                                 .collect(Collectors.toList());
            
        List<Post> ultimosPosts = new ArrayList<Post>();
        int index = 0;
        Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
        while (postIterator.hasNext() &&  index < cantidad) {
            ultimosPosts.add(postIterator.next());
        }
        return ultimosPosts;
    }
  ```
### Bad smell: Reinventando la rueda 
```Java
     List<Post> ultimosPosts = new ArrayList<Post>();
     int index = 0;
     Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
     while (postIterator.hasNext() &&  index < cantidad) {
        ultimosPosts.add(postIterator.next());
     }
  ```
1. Aplico replace loop with pipeline 
2. Reemplazo la asignación de `ultimosPosts` por el nuevo stream
```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {
        List<Post> postOtrosUsuarios = this.posts.stream()
                                                 .filter(post-> !post.getUsuario().equals(user))
                                                 .collect(Collectors.toList());
        List<Post> postOtrosUsuariosOrdenados = postOtrosUsuarios.stream()
                                                                 .sorted((post1, post2) -> post2.getFecha().compareTo(post1.getFecha()))
                                                                 .collect(Collectors.toList());
        List<Post> ultimosPost = postOtrosUsuariosOrdenados.stream()
                                                           .limit(cant)
                                                           .collect(Collectors.toList());
        return ultimosPost;
    }
  ```
### Bad smell: Temporary field `postOtrosUsuarios`, `ultimosPost` y `postOtrosUsuariosOrdenados`
1. Elimino las variables retornando directamente una unificación de los tres streams
```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {
        return this.posts.stream()
                         .filter(post -> !post.getUsuario().equals(user))
                         .sorted((post1, post2) -> post2.getFecha().compareTo(post1.getFecha()))
                         .limit(cantidad)
                         .collect(Collectors.toList());
    }
```