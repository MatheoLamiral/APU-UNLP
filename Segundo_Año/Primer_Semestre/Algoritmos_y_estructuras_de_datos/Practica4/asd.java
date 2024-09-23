public static int[] randomUno(int n) {
    int i, x = 0, k;
    int[] a = new int[n];
    for (i = 0; i < n; i++) {
        //Itera n-1 veces
        boolean seguirBuscando = true;
        while (seguirBuscando) {
            //Genera un entero random entre 0 y n-1
            x = ran_int(0, n - 1);
            seguirBuscando = false;
            for (k = 0; k < i && !seguirBuscando; k++)
                //Recorre el vector
                //Si el random está en el vector
                //sale del for
                if (x == a[k])
                    seguirBuscando = true;
            //
        }
        a[i] = x;
    }
    return a;
}
