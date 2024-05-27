Program P2p16;
uses
    SysUtils;
const
    dimf=2;//10
    valoralto=9999;
type

    moto = record
        cod:integer;
        nom:string;
        desc:string;
        modelo:string;
        marca:string;
        stock_act:integer;
    end;

    venta = record
        cod_moto:integer;
        precio:real;
        fecha:integer;
    end;

    ArchivoMaestro = file of moto;
    ArchivoDetalle = file of venta;

    vecDet = array[1..dimF] of ArchivoDetalle;
    vecReg = array[1..dimF] of venta;

    Procedure crearMaestro(var mae:ArchivoMaestro);
    var 
        txt:text;
        m:moto;
    begin
        Assign(mae,'archivoMotos');
        Assign(txt,'mae.txt');
        reset(txt);
        rewrite(mae);
        while(not eof(txt))do
        begin
            with m do
            begin
                readln(txt, cod,stock_act,nom);
                readln(txt, desc);
                readln(txt, modelo);
                readln(txt, marca);
                write(mae, m);
            end;
        end;
        writeln('Archivo maestro creado con exito!');
        close(mae);
        close(txt);
    end;

    procedure crearUnDetalle(var det:ArchivoDetalle;nombre_txt:string;nombre_binario:string);
    var
        txt:text;
        v:venta;
    begin
        assign(txt, nombre_txt);
        assign(det, nombre_binario);
        reset(txt);
        rewrite(det);
        while(not eof(txt)) do
        begin
            with v do
                begin
                    readln(txt, cod_moto,precio,fecha);
                    write(det, v);
                end;
        end;
        writeln('Archivo binario detalle creado con exito!');
        close(det);
        close(txt);
    end;

    procedure crearDetalles(var vec: vecDet);
    var
        i: integer;
    begin
        for i:= 1 to dimF do 
            crearUnDetalle(vec[i],'det'+IntToStr(i)+'.txt','detalle'+IntToStr(i));
    end;

    procedure leer(var det: ArchivoDetalle; var reg: venta);
    begin
        if(not eof(det)) then
            read(det, reg)
        else
            reg.cod_moto := valoralto;
    end;

    procedure minimo(var v: vecDet; var vecReg: vecReg; var min: venta);
    var
        i, pos: integer;
    begin
        min.cod_moto:= valoralto;
        for i:= 1 to dimF do
            if (vecReg[i].cod_moto < min.cod_moto) then
                begin
                    min:= vecReg[i];
                    pos:= i;
                end;
        if(min.cod_moto <> valoralto) then
            leer(v[pos], vecReg[pos]);
    end;

    Procedure actualizarMaestro(var mae:ArchivoMaestro;var det:vecDet);
    var
        min:venta;
        reg_mae:moto;
        vec_reg:vecReg;
        max_venta,max_cod,venta_moto,i:integer;
    begin
        max_venta:=-1;
        max_cod:=-1;
        reset(mae);
        for i:=1 to dimF do 
        begin
            reset(det[i]);
            leer(det[i],vec_reg[i]);
        end;
        minimo(det,vec_reg,min);
        Read(mae,reg_mae);
        while(min.cod_moto <> valoralto) do
        begin
            venta_moto:=0;
            while(not eof(mae))and(min.cod_moto<>reg_mae.cod)do Read(mae,reg_mae);
            while(min.cod_moto = reg_mae.cod) do
            begin
                venta_moto:=venta_moto+1;
                minimo(det,vec_reg,min);
            end;
            reg_mae.stock_act:= reg_mae.stock_act - venta_moto;
            if(venta_moto>max_venta)then 
            begin
                max_venta:=venta_moto;
                max_cod:=reg_mae.cod;
            end;
            seek(mae, filepos(mae)-1);
            Write(mae,reg_mae);
        end;
        writeln('el codigo de la moto mas vendida es: ',max_cod);
        close(mae);
        for i:= 1 to dimF do
            close(det[i]);
    end;

    Procedure imprimirArchivo(var arch:ArchivoMaestro);
	var
		reg:moto;
	begin
		reset(arch);
		while(not eof(arch))do
		begin
			Read(arch,reg);
			writeln('COD:',reg.cod,', NOMBRE:',reg.nom,', DESC:',reg.desc,', MODELO:',reg.modelo,
                    ', MARCA:',reg.marca,', STOCK_ACT:',reg.stock_act);
		end;
		close(arch);
	end;

var
    maestro:ArchivoMaestro;
    detalles:vecDet;
BEGIN
    crearMaestro(maestro);
    crearDetalles(detalles);
    actualizarMaestro(maestro,detalles);
    imprimirArchivo(maestro);
END.