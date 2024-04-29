import introduceDatos.Pregunta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio01 {
    public static void main(String[] args) throws IOException { // función principal encargada de generar el menu y llamar a los submetodos.
        int opcion = 0;
        Scanner entrada = new Scanner(System.in);

            //Primero creamos la ruta del proyecto:
            String rutaProyecto = System.getProperty("user.dir"); //ruta hasta el proyecto donde se ejecuta nuestro programa
            String separador = File.separator; //String que utiliza el Sistema Operativo para separar carpetas al generar su ruta

            //segundo creamos la ruta de la carpeta que contiene la del proyecto:
            String rutaCarpeta = rutaProyecto + separador + "archivos";
            System.out.println(" La ruta de la carpeta es: " + rutaCarpeta);

            File carpeta = new File(rutaCarpeta); //el objeto File nos permite también tener información sobre la carpeta
            // Creando la carpeta nuevaCarpeta si no existe.
            if (!carpeta.exists()) { // if(carpeta.exists()==false)
                    carpeta.mkdir(); // make directory
            }

        do {
            System.out.println("1- Nuevo archivo.");
            System.out.println("2- Listar archivos. ");
            System.out.println("3- Muestra un archivo.");
            System.out.println("4- Borrar un archivo.");
            System.out.println("5- Renombrar un archivo.");
            System.out.println("6- Modificar un archivo.");
            System.out.println("7- Salir.");

            opcion = Pregunta.pideEntero("Introduzca la operación a realizar de las anteriores: ", entrada);

            switch (opcion) {
                case 1:
                    crearArchivo(rutaCarpeta, separador);
                    break;
                case 2:
                    listarArchivos(separador, rutaCarpeta);
                    break;
                case 3:
                    muestraArchivo(separador, rutaCarpeta);
                    break;
                case 4:
                    borrarArchivo(separador, rutaCarpeta);
                    break;
                case 5:
                    renombrarArchivo(separador, rutaCarpeta, entrada);
                    break;
                case 6:
                    reemplazarCaracteres(rutaCarpeta, separador, entrada);
                    break;
                case 7:
                    System.out.println("adiós");
                    break;

                default:
                    System.out.println("Ha introducido un valor incorrecto, por favor inserte de nuevo");
            }

        } while (opcion != 7);
        entrada.close();
    }

    public static void crearArchivo(String rutaCarpeta, String separador) throws IOException { 
        /**
         * Pide al usuario que indique el nombre del archivo y el texto a introducir
         * @param entrada String con la ruta de la carpeta y el separador los cuales necesitamos para conocer la ruta en donde se va a crear el archivo.
         */
        
        Scanner entrada = new Scanner(System.in);
        FileWriter fw;
        String nombreArchivo;
        String textoArchivo;
       
        System.out.println("Por favor indica el nombre del archivo que quiere crear");
        nombreArchivo= entrada.nextLine();
        try{    
            File newFile= new File(rutaCarpeta+separador+ nombreArchivo);
            newFile.createNewFile();

        }catch (IOException e) {
            System.out.println("Se ha producido el siguiente error al intentar escribir en el archivo");
        }
          
        System.out.println("Introduce un texto");
        textoArchivo= entrada.nextLine();   
        fw= new FileWriter(rutaCarpeta + separador + nombreArchivo); // Creamos un Stream de escritura vinculado con el archivo en el que queremos escribir    
        BufferedWriter bw= new BufferedWriter(fw); //Creamos un Buffer de escritura vinculado con el Stream. Esto nos permite escribir String en el buffer y no bytes.
        bw.write(textoArchivo);
        bw.flush(); //obliga al buffer a escribir en el archivo todo quello que contenga el buffer. (lo vacia)
        bw.close(); //vacia el buffer y libera el archivo cerrando la conexion con él.
    }
    
    public static String[] listarArchivos(String separador, String rutaCarpeta) { 
        /** Creamos una función de tipo Array para listar los archivos.
         * @param entrada String con la ruta de la carpeta y el separador los cuales necesitamos para conocer la ruta en donde van a estar los archivos.
         * Este método muestra por consola el listado de los archivos númerados.
         * @return el Array con el listado de archivos.
         */
    
        String[] archivosList;
        //Obtenemos un array con los nombres de archivos y directorios
        File carpeta= new File(rutaCarpeta);
        archivosList= carpeta.list(); // crea un array de Strings con los nombres de los archivos y carpetas dentro del carpeta definida en la variable File.
        for (int i=0; i< archivosList.length; i++) {
            System.out.println(i + " -" + archivosList[i]);
        }
        System.out.println();
        return archivosList;
    }

    public static void muestraArchivo(String separador, String rutaCarpeta) throws IOException {  
        /** Creamos una función que pida el usuario la posición del archivo que desea ver y se lo muestra.
         * @param entrada String con la ruta de la carpeta y el separador los cuales necesitamos para conocer la ruta en donde va a estar el archivo seleccionado.
         * Este método muestra por consola el contenido del archivo.
         */
        int i = -1;
        Scanner entrada = new Scanner(System.in);
        String[] listado= listarArchivos(separador, rutaCarpeta);   //llamamos al array con el listado de archivos de la carpeta "archivos" 
        do{
            try{
                System.out.println("Elige la posición del archivo que quieres ver");
                i= entrada.nextInt();
            }catch (Exception e) {
                System.out.println("Introduzca una posición correcta");
            }
        }while((i < 0) || (i > listado.length-1));
        
        File archivo = new File(rutaCarpeta+separador+listado[i]);
        FileReader fr= new FileReader(archivo); // Creamos un Stream de lectura vinculado con el archivo en el que queremos ver
        BufferedReader br = new BufferedReader(fr); //Creamos un Buffer de lectura vinculado con el Stream.
        String contenido = br.readLine();
        System.out.println(contenido);
        br.close();
        
    }

    public static void borrarArchivo(String separador, String rutaCarpeta) throws IOException {
        /** Creamos una función que pida el usuario la posición del archivo que desea borrar y lo borra.
         * @param entrada String con la ruta de la carpeta y el separador los cuales necesitamos para conocer la ruta en donde va a estar el archivo seleccionado.
         * Este método muestra borra el archivo seleccionado por el  usuario.
         */
        Scanner entrada = new Scanner(System.in);
        int i = -1;
        String[] listado= listarArchivos(separador, rutaCarpeta);    //llamamos al array con el listado de archivos de la carpeta "archivos" 
        
        do{
            try{
                System.out.println("Elige la posición del archivo que quieres borrar");
                i= entrada.nextInt();
            }catch (Exception e) {
                System.out.println("Introduzca una posición correcta");
            }
        }while((i < 0) || (i > listado.length-1));    
        
        File archivo = new File(rutaCarpeta+separador+listado[i]);
        archivo.delete();
        System.out.println("Se ha borrado el archivo correctamente");
    }    

    public static void renombrarArchivo(String separador, String rutaCarpeta, Scanner entrada) throws IOException {
        /** Creamos una función que pida el usuario la posición del archivo al que le desea cambiar el nombre
         * @param entrada String con la ruta de la carpeta y el separador los cuales necesitamos para conocer la ruta en donde va a estar el archivo seleccionado + la entrada del scanner
         * Este método modifica el nombre del archivo y lo muestra por consola.
         */
        int i = -1;
        String nuevoNombre;
        boolean encontrado = false;
        String[] listado= listarArchivos(separador, rutaCarpeta);    
        do{
            i = Pregunta.pideEntero("Elige la posición del archivo al que le quieres modificar el nombre ", entrada); // control de error en pideEntero
        }while((i < 0) || (i > listado.length-1));

        do{
            System.out.println("Elige el nuevo nombre del archivo"); 
            nuevoNombre= entrada.nextLine();
            encontrado = false;
            for ( int j =0; j< listado.length; j++) { // En los strings el == no va afuncionar porque se intenta comparar si es el mismo objeto
                if(nuevoNombre.equals(listado[j])){ // por eso en su lugar usamos la funcion .equals 
                    System.out.println("El nombre del archivo ya existe, por favor introduce otro");
                    encontrado = true;
                    break;
                }    
            }
        }while(encontrado == true);     
        
        File archivo = new File(rutaCarpeta+separador+listado[i]);
        File newName = new File(rutaCarpeta+separador+nuevoNombre);
        archivo.renameTo(newName);
        System.out.println("Se ha cambiado el nombre del archivo por " + nuevoNombre); 
    }    

    public static void reemplazarCaracteres(String rutaCarpeta, String separador, Scanner entrada) {
            /** Creamos una función que reemplace un caracter por otro que elija el usuario.
         * @param entrada String con la ruta de la carpeta y el separador los cuales necesitamos para conocer la ruta en donde va a estar el archivo seleccionado + la entrada del scanner
         * Este método modifica el caracter por otro que elija el usuario.
         */    
        String[] listados = listarArchivos(separador, rutaCarpeta);
        
        if (listados.length > 0) {
            int i = -1;
            do{
                i = Pregunta.pideEntero("Indica el número del archivo a modificar", entrada);
            }while((i < 0) || (i > listados.length-1));
    
            File archivo = new File (rutaCarpeta+separador+listados[i]); //2)Indicando como 1er parámetro una variable tipo File definida con la ruta del archivo al que acceder.
            String text_find, text_replace;
            try {
                
                do{
                    System.out.println("Qué caracter desea modificar, elige solo un caracter");
                    text_find = entrada.nextLine();
                }while (text_find.length() !=1); //char_replace=text.toCharArray()[0];

                do{
                    System.out.println("Indica el nuevo caracter. Solo un caracter");
                    text_replace = entrada.nextLine();
                }while (text_replace.length() !=1); //char_replace=text.toCharArray()[0];
        
                RandomAccessFile raf = new RandomAccessFile(archivo, "rw"); // raf: variable del tipo RandomAccessFile por defecto tiene asociado un puntero que apunta a una posición de memoria. 
                //rw variable modo acceso al archivo lectura y escritura
                
                //imprimim el texto del fichero
                while (raf.getFilePointer() < raf.length()) { //Retorna la posicion actual del puntero, inicialmente 0. // mientras el puntero no llegue al final del archivo (texto dentro del fichero), se va a ir ejecutando
                    Character chr = (char)raf.readByte(); // readbyte: cada caractaer que va encontrando lo va almacenando + Aumenta en 1 el puntero
                    if(chr.toString().compareTo(text_find)==0){ // si el caracter que coge es el que hemos dicho de modificar entonces hace lo siguiente
                        raf.seek(raf.getFilePointer()-1); //si lo que encontraste era el caracter que estaba buscando aqui vas para atras otra vez-mover el puntero a la posicion 0 del archivo
                        raf.writeBytes(text_replace);	 //a partir del puntero se se sustituyen los bytes siguientes por cada carácter del String pasado como parámetro. Reemplaza el caracter
                    }   
                }
                raf.close(); //Trancamos el fichero
                System.out.println("Se ha modificado correctamente");
            } catch (IOException el) {
                System.out.println("Se ha producido un error al intentar escribir en el archivo" + listados[i]);
                System.out.println("El error producido es el siguiente" + el.getMessage());	
            }
        } else {
            System.out.println("Carpeta vacia");
        }	
    }       
}



