Program Merge;

const
    dimF = 3;
    valoralto = 9999;
type

    venta = record
        cod: integer;
        nom: string;
        cant: integer;
        fecha_y_hora: string;
    end;    

    infoMae = record
        cod: integer;
        nom: string;
        cant_total: integer;
    end;

    ArchivoDetalle = file of venta;
    ArchivoMestro = file of infoMae;

var

begin

end;