# Sistema de Gestión de Biblioteca

## Compilación y ejecución

1) Abrir el proyecto en un IDE compatible
2) Ejecutar la clase Main. 
3) El sistema se ejecuta por consola mediante un menú interactivo.

------Desde consola------
1) Ubicarse en la carpeta raíz del proyecto. 
2) Compilar los archivos:
   javac -d out src/*.java src/models/*.java src/models/loan/*.java src/models/member/*.java src/models/reservation/*.java src/utils/*.java
3) Ejecutar el programa:
   java -cp out Main
4) El sistema se ejecuta por consola mediante un menú interactivo.

## Funcionalidades implementadas:

### Funcionalidades pedidas:
1) Listar libros: recorre la lista de libros mostrando cada libro 
2) Buscar libros: se puede buscar por titulo o autor, por titulo muestra solo un libro y por autor muestra la lista de libros de ese autor
3) Listar socios: lista de socios mostrando cada socio
4) Alta / Baja de socio: el usuario ingresa la opcion 1 Alta 2 Baja o 0 Volver y en base a la eleccion varia el estado active en Member
5) Registrar préstamo: se chequea estado del socio, cantidad maxima de prestamos, disponibilidad de ejemplares si no hay suficiente se pregunta si se quiere reservar
6) Registrar devolución:se chequea si ese miembro tiene ese libro en un prestamo ACTIVO,se chequea si se devolvio fuera de termino si esto ocurre se realiza la multa, 
luego se realiza el prestamo instantaneo a la primer reserva (si es que el miembro que realizo la reserva puede tener otro prestamo)
7) Listar préstamos activos: chequea prestamos activos y los muestra
8) Listar préstamos vencidos: chequea prestamos vencidos y los muestra
9) Reservar libro: crea una reserva y la añade a la lista de reservas 
10) Listar reservas activas: chequea que el estado sea activo y se muestra cada reserva activa
11) Pagar multa: el miembro seleccionado paga el total de su balance en multas
0) Salir: Se termina el programa


### Funcionalidades auxiliares:
1) selectMember(): recorre la lista de miembros y muestra un numero y el miembro, le pide al usuario que seleccione el numero de la opcion, retorna el miembro seleccionado
2) selectBook(): recorre la lista de libros y muestra un numero y el libro, le pide al usuario que seleccione el numero de la opcion, retorna ese libro
3) Se creo la clase RandomHandler para centralizar la creacion de IDs aleatorios
4) LoanStatus, MemberType y ReservationStatus fueron creados como enums 
5) returnMenu() se creo para que sea mas legible la consola
6) checkAllLoanOverdue() recorre la lista de prestamos y en cada uno se realiza checkLoanOverdue(loan)
7) checkAllLoanOverdue() se realiza cada vez que se vuelve al menu para evitar que aparezcan como activos prestamos que estan vencidos
8) listLoans(STATUS,listaPrestamos) recibe un status por si en un futuro se quiere listar los prestamos que por ejemplo esten devueltos, se pueda hacer facilmente
9) listReserves(STATUS, listaReservas) recibe el status por si en un futuro se quiere listar las reservas ya sean canceladas o cumplidas

**Chequear prestamo vencido**: las lineas 21 a 23 en el Main estan comentandas, si se descomenta se puede chequear que pasa cuando un prestamo esta vencido