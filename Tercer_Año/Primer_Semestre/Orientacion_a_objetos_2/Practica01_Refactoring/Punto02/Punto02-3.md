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
# Bad smell: Reinventando la rueda 
- ```Java      
    List<Post> postsOtrosUsuarios = new ArrayList<Post>();
    for (Post post : this.posts) {
        if (!post.getUsuario().equals(user)) {
            postsOtrosUsuarios.add(post);
  ```
### Aplico replace loop with pipeline
- ```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {
            
        List<Post> postOtrosUsuarios = this.post.stream().filter(post-> !post.getUsuario().equals(user)).collect(Collectors.toList());
            
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
# Bad smell: Reinventando la rueda
- ```Java
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
  ```
### Aplico replace loop with pipeline
- ```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {
            
        List<Post> postOtrosUsuarios = this.post.stream().filter(post-> !post.getUsuario().equals(user)).collect(Collectors.toList());
            
        List<Post> postOtrosUsuariosOrdenados = postOtrosUsuarios.stream().sorted((post1, post2) -> post2.getFecha().compareTo(post1.getFecha())).collect(Collectors.toList());
            
        List<Post> ultimosPosts = new ArrayList<Post>();
        int index = 0;
        Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
        while (postIterator.hasNext() &&  index < cantidad) {
            ultimosPosts.add(postIterator.next());
        }
        return ultimosPosts;
    }
  ```
# Bad smell: Reinventando la rueda 
- ```Java
     List<Post> ultimosPosts = new ArrayList<Post>();
        int index = 0;
        Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
        while (postIterator.hasNext() &&  index < cantidad) {
            ultimosPosts.add(postIterator.next());
        }
        return ultimosPosts;
  ```
### Aplico replace loop with pipeline 
- ```Java
    public List<Post> ultimosPosts(Usuario user, int cantidad) {
        List<Post> postOtrosUsuarios = this.posts.stream().filter(post-> !post.getUsuario().equals(user)).collect(Collectors.toList());
        List<Post> postOtrosUsuariosOrdenados = postOtrosUsuarios.stream().sorted((post1, post2) -> post2.getFecha().compareTo(post1.getFecha())).collect(Collectors.toList());
        List<Post> ultimosNPost = postOtrosUsuariosOrdenados.stream().limit(cant).collect(Collectors.toList());
        return ultimosNPost;
    }
  ```