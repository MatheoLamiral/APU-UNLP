# Ejercicio 10

## Inciso a 
```Pascal
Program Uno;
    var x:integer;
    Function Dos:integer;
    begin
        x:= x + 1;
        return (x);
    end;
    Function Tres(x:integer):integer;
    begin
        x:= x + 5;
        x:= Dos + 10;
        return x;
    end;
begin
    x:= 8; 
    x:= Tres(x);
    write (x);
end.

```
## Inciso b
```ADA
procedure Ejemplo is

   X : Integer := 5;

   function Dos return Integer is
   begin
      X := X + 1;
      return X;
   end Dos;

   procedure Tres (x: in out Integer) is
   begin
      x := x + 5;
      x := Dos + 10;
   end Tres;

begin
   x := 8;
   Tres(x);
   Put_Line("X = " & Integer'Image(X));
end Ejemplo;

```