package modeloGrafico.tablasConsulta;

import static app.funProp.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import modeloGrafico.consulta.Consulta;
import modeloGrafico.crearCliente.CrearCliente;

import java.awt.Container;
import java.awt.event.*;
import java.util.HashMap;

public class TablasConsulta extends JFrame implements ActionListener{
    private JPanel JpanelTablas;
    private JComboBox cBoxEntidad;
    private JTable tablaEntidad;
    private JButton btnAgregar;
    public JButton btnRegresar;
    private JLabel lblEntidad;
    private JLabel lblTitulo;
    private JButton btnEliminar;
    private JButton btnModificar;
    private JButton btnAddFila;
    private String[][] datosEntidad;
    private String[] encabezadosEntidad;
    private String[][] datosVacios;
    private String[] titulosVacios;
    private String[] entidades;
    private ENTIDAD entidad;
    private DefaultTableModel modelEntidad; 
    private ACCION accion;
    private int fila;
    private String valuePK;
    private int colPK;  //columna donde se mostrara el dato de la primaryKey
    private boolean editablePK;
    private String tabla;
    private String[] columnasSQL;
    private String txtColSQL;
    private int[] editableColumns;
    private int[] tempEditableColumns;
    private String[] decoradorDatos;
    private JComboBox cBoxProveedor;
    private JComboBox cBoxBicicleta;
    private JComboBox cBoxMoto;
    private JComboBox cBoxAlias;
    private JComboBox cBoxTipo;
    private JComboBox cBoxFabricante;
    private String tipoSeleccionado;
    private HashMap<String, JComboBox> comboBoxs;
    private int[] columnsJcbox;
    private int[] tempColumnsJcbox;
    private String[] implementJcbox;
    private String[] tempImplementJcbox;
    private TableColumn columnTable;
    private TableCellEditor tableCellEditor ;
    private int cont;
    private int filaPrevia;
    private int columnaPrevia;
    private ImprimirEntidad imprimir;
    private String queryCreate;

    public JButton btnSalir;
    private JMenuBar menuBar;
    private JMenu menu1, menu2;
    private JMenuItem mi11, mi12, mi13, mi14;
    private JMenuItem mi21;

    //atributos de la clase administrador
    public boolean desplegar;
    public int opcion;
    private int[] limites;

    //atributos de otros form
    public CrearCliente crearCliente;
    public Consulta intencion;
    public TablasConsulta tablasConsulta;
    public IngresoPK ingreso;


    //CONSTRUCTOR
    public TablasConsulta(){
        datosVacios = new String[][]{{}};
        titulosVacios = new String[]{};
        entidades = new String[]{"Elija una entidad", ""+ENTIDAD.CLIENTE, ""+ENTIDAD.BICICLETA, ""+ENTIDAD.MOTO, ""+ENTIDAD.PROVEEDOR, ""+ENTIDAD.INTENCIONES};
        desplegar = false;
        crearCliente = new CrearCliente();
        intencion = new Consulta();
        imprimir = new ImprimirEntidad();
        ingreso = new IngresoPK();
        

        //EVENT LISTENERS
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarDato();
            }
        });
        
        btnAddFila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarFila();
            }
        });
        
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                setDefault();
                cont = 0;
            }
        });

        cBoxEntidad.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                cargarTabla();
                cont = 0;
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accion = ACCION.MODIFICAR;
                modificarDato();
                cont = 0;
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accion = ACCION.ELIMINAR;
                eliminarDato();
            }
        });

        tablaEntidad.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                eventoDobleClickJbox();
            }
        });
    
        imprimir.btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarTabla();
            }
        });
    
        ingreso.btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {tablaEntidad.setValueAt(ingreso.getValuePK(), 0, colPK);}
        });

        ingreso.txtPK.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){tablaEntidad.setValueAt(ingreso.getValuePK(), 0, colPK);}
            }
        });
    
    }

    //agrega una fila a la tabla de la entidad correspondiente. si la PK es editable se abre una ventana para solicitarla. si no la BD la asigna.
    protected void agregarFila() {

        if(editablePK){
            valuePK = null;
            ingreso.setTextPK(encabezadosEntidad[colPK]);
            ingreso.desplegarForm();
        }

        if(entidad == ENTIDAD.INTENCIONES) {modelEntidad.insertRow(0,new String[]{null,null,"BICICLETA",TimeNow(),null});}
        else{
            modelEntidad.insertRow(0,new String[encabezadosEntidad.length]);
        }
        
    }

    //agrega dato a la BD
    protected void agregarDato() {
        fila = 0;
        String[] temporal = new String[encabezadosEntidad.length];
        
        if(fila != -1){
            for (int i = 0; i < datosEntidad[fila].length; i++) {
                if(i == colPK){valuePK = decoradorDatos[i] + (String) tablaEntidad.getValueAt(fila, i) + decoradorDatos[i];}
                temporal[i] = decoradorDatos[i] +  (String) tablaEntidad.getValueAt(fila, i) + decoradorDatos[i];
            }
            valuePK = "\"" +valuePK+ "\"";
            queryCreate = (editablePK)?"INSERT INTO "+tabla+"("+arregloSeparator(columnasSQL)+") VALUES ("+arregloSeparator(temporal) +")":
            "INSERT INTO "+tabla+"("+arregloSeparator(columnasSQL,colPK)+") VALUES ("+arregloSeparator(temporal,colPK) +")";
            if(!myJDBC.CREATE(queryCreate)){JOptionPane.showMessageDialog(null, "No se puede agregar item.\nRevise la informacion ingresada.");}
            else{System.out.println("registro creado");}
        }
        else{JOptionPane.showMessageDialog(null, "no hay item seleccionado");}
        cargarTabla();
    }

    //permite leer los eventos asociados a la barra menu superior de la ventana
    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        entidad = ENTIDAD.NULO; accion= ACCION.SALIR;
        if (e.getSource() == mi11) {JOptionPane.showMessageDialog(JpanelTablas, "CREAR CRUD PARA GUARDAR TODO");}
        if (e.getSource() == mi12) {cargarTabla();}
        if (e.getSource() == mi13) {imprimir();}
        if (e.getSource() == mi21) {JOptionPane.showMessageDialog(JpanelTablas, "ALTERNATIVA");}
    }

    //permite editar o modificar el dato del combobox siempre al dar doble clic en una celda determinada
    protected void eventoDobleClickJbox() {
        if(entidad != ENTIDAD.NULO){
            int col = tablaEntidad.getSelectedColumn();
            int fila = tablaEntidad.getSelectedRow();

            if(fila == filaPrevia){cont++;}
            columnaPrevia = col;
            filaPrevia = fila;

            if(cont == 2){
                editableColumns = tempEditableColumns.clone();
                columnsJcbox = tempColumnsJcbox.clone();
                implementJcbox = tempImplementJcbox.clone();
                cont = 0;
            } else {
                editableColumns = new int[]{};
                columnsJcbox = new int[]{};
                implementJcbox = new String[]{};
            }
            if(entidad == ENTIDAD.INTENCIONES){
                tipoSeleccionado = (((String) tablaEntidad.getValueAt(fila, 2)).equals("BICICLETA") )?"bicicleta":"moto";
            }
            cargarCboxes();
        }
    }

    //elimina los registros seleccionados en la Jtable de la BD.
    protected void eliminarDato() {
        fila = tablaEntidad.getSelectedRow();
        
        if(fila != -1){
            valuePK = datosEntidad[fila][colPK];
            valuePK = "\"" +valuePK+ "\"";
            String query = "DELETE FROM "+tabla+ " WHERE " + columnasSQL[colPK] + " = " + valuePK;
            System.out.println(query);
            if(!myJDBC.DELETE(query)){JOptionPane.showMessageDialog(null, "No se puede eliminar item.\nItem con cruce en otra tabla");}
            else{cargarTabla();}
        }
        else{JOptionPane.showMessageDialog(null, "no hay item seleccionado");}
    }
    
    //modifica los datos en la BD de la fila donde esta seleccionada del Jtable.
    protected void modificarDato() {
        fila = tablaEntidad.getSelectedRow();
        String[] temporal = new String[encabezadosEntidad.length];
        
        if(fila != -1){
            for (int i = 0; i < datosEntidad[fila].length; i++) {
                if(i == colPK){valuePK = (String) tablaEntidad.getValueAt(fila, i);}
                temporal[i] = (String) tablaEntidad.getValueAt(fila, i);
            }
            valuePK = "\"" +valuePK+ "\"";
            String query = "UPDATE " + tabla + " SET " + txtConsulta(columnasSQL,temporal,colPK) + " WHERE "+ columnasSQL[colPK] +" = " + valuePK;
            if(!myJDBC.UPDATE(query)){JOptionPane.showMessageDialog(null, "No se puede modificar item.\nRevise la informacion editada.");};
        }
        else{JOptionPane.showMessageDialog(null, "no hay item seleccionado");}
        cargarTabla();
    }

    //usado para modificar dato en la jtabla y actualizarlo en la BD. crea el txto despues del SET, donde queda columna="valor"
    private String txtConsulta(String[] columnasSQL, String[] temporal, int colPK) {
        String salida = "";
        for (int i = 0; i < columnasSQL.length; i++) {
            if(i != colPK){
                salida += columnasSQL[i] + "=\"" + temporal[i] + "\"";
                salida += (i != columnasSQL.length-1)?",":"";
            }
        }
        return (columnasSQL.length-1 == colPK)?salida.substring(0,salida.length()-1):salida;
    }

    //llama e inicializa los datos para llenar el modelo de la tabla
    public void cargarTabla() {
        String opcion = (String) cBoxEntidad.getSelectedItem();
        filaPrevia = -1;
        columnaPrevia = -1;

        switch (opcion) {
            case "CLIENTE":
            entidad = ENTIDAD.CLIENTE;
            colPK = 0;
            tabla = "clientes";
            editablePK = true;
            columnasSQL = new String[]{"alias","nombre","apellido","telefono","contrasena"};
            encabezadosEntidad = new String[]{"ALIAS", "NOMBRE", "APELLIDO", "TELEFONO", "CONTRASENA"};
            decoradorDatos = new String[]{"'","'","'","'","'"};
            editableColumns = new int[]{1,2,3,4};
            columnsJcbox = new int[]{};
            implementJcbox = new String[]{};
            break;

            case "BICICLETA":
            entidad = ENTIDAD.BICICLETA;
            colPK = 0;
            editablePK = true;
            encabezadosEntidad = new String[]{"FABRICANTE", "PRECIO_UNITARIO", "ANIO"};
            decoradorDatos = new String[]{"'","",""};
            tabla = "bicicletas";
            columnasSQL = new String[]{"fabricante","precio_unitario","anio"};
            editableColumns = new int[]{1,2};
            columnsJcbox = new int[]{};
            implementJcbox = new String[]{};
            break;

            case "MOTO":
            entidad = ENTIDAD.MOTO;
            colPK = 0;
            editablePK = true;
            encabezadosEntidad = new String[]{"FABRICANTE", "PRECIO_UNITARIO", "AUTONOMIA", "PROVEEDOR"};
            decoradorDatos = new String[]{"'","","",""};
            tabla = "motocicletas_electricas";
            columnasSQL = new String[]{"fabricante","precio_unitario","autonomia","proveedor"};
            editableColumns = new int[]{1,2,3,4};
            columnsJcbox = new int[]{3};
            implementJcbox = new String[]{"proveedor"};
            break;

            case "PROVEEDOR":
            entidad = ENTIDAD.PROVEEDOR;
            colPK = 0;
            encabezadosEntidad = new String[]{"ID", "NOMBRE", "DIRECCION", "TELEFONO"};
            editablePK = false;
            decoradorDatos = new String[]{"","'","'","'"};
            tabla = "proveedor";
            columnasSQL = new String[]{"id","nombre","direccion","telefono"};
            editableColumns = new int[]{1,2,3};
            columnsJcbox = new int[]{};
            implementJcbox = new String[]{};
            break;

            case "INTENCIONES":
            entidad = ENTIDAD.INTENCIONES;
            colPK = 4;
            editablePK = false;
            encabezadosEntidad = new String[]{"CLIENTE", "FABRICANTE", "TIPO", "FECHA", "ID"};
            decoradorDatos = new String[]{"'","'","'","'",""};
            tabla = "intencion_compra";
            columnasSQL = new String[]{"cliente","fabricante","tipo","fecha","id"};
            editableColumns = new int[]{0,1,2,3};
            columnsJcbox = new int[]{0,1,2};
            implementJcbox = new String[]{"alias","fabricante","tipo"};
            break;

            case "Elija una entidad":
            entidad = ENTIDAD.NULO;
            colPK = -1;
            break;

            default:
            break;
        }
        if(entidad != ENTIDAD.NULO){
            tempEditableColumns = editableColumns.clone();
            tempColumnsJcbox = columnsJcbox.clone();
            tempImplementJcbox = implementJcbox.clone();
            txtColSQL = arregloSeparator(columnasSQL);
            datosEntidad = toMatrixString(menuConsultarGeneral(entidad,tabla,txtColSQL), columnasSQL.length);
        }
        cargarModelo();
    }

    //carga los comboboxes segun corresponda en la tabla
    private void cargarCboxes() {
        if(entidad != ENTIDAD.NULO){
            cargarHashmapCboxes();
            for (int i = 0; i < columnsJcbox.length; i++) { //carga comboboxes a las celdas
                columnTable = tablaEntidad.getColumnModel().getColumn(columnsJcbox[i]);
                tableCellEditor = new DefaultCellEditor(comboBoxs.get(implementJcbox[i]));
                columnTable.setCellEditor(tableCellEditor);
            }
        } 
    }

    //carga los datos en el modelo e inicializa la tabla
    private void cargarModelo() {
        if(entidad != ENTIDAD.NULO){
            modelEntidad = new DefaultTableModel(datosEntidad, encabezadosEntidad){
                public boolean isCellEditable(int row, int column) {    //establece que columnas son editables 
                    for (int i = 0; i < editableColumns.length; i++) {
                        if(column == editableColumns[i]){return true;}
                    }
                    return false;
                }
            };
            tablaEntidad.setModel(modelEntidad);
        } else {
            modelEntidad = new DefaultTableModel(datosVacios, titulosVacios);
            tablaEntidad.setModel(modelEntidad);}
    }

    //crea un hashmap con llaves de los nombres de las entidades y con valores los comboboxes.
    private void cargarHashmapCboxes() {
        cBoxProveedor = new JComboBox(arraylistToList(menuConsultarGeneral(ENTIDAD.PROVEEDOR, "proveedor", "id"), 0));
        cBoxAlias = new JComboBox(arraylistToList(menuConsultarGeneral(ENTIDAD.CLIENTE, "clientes", "alias"), 0));
        cBoxBicicleta = new JComboBox(arraylistToList(menuConsultarGeneral(ENTIDAD.BICICLETA, "bicicletas", "fabricante"), 0));
        cBoxMoto = new JComboBox(arraylistToList(menuConsultarGeneral(ENTIDAD.MOTO, "motocicletas_electricas", "fabricante"), 0));

        try {
            cBoxFabricante = (tipoSeleccionado.equals("bicicleta"))?cBoxBicicleta:cBoxMoto;
        } catch (Exception e) {
            // System.out.println("error");
            String[] listaVacia = new String[]{};
            cBoxFabricante = new JComboBox(listaVacia);
        }
        
        String[] tipos = new String[]{"BICICLETA","MOTO"};
        cBoxTipo = new JComboBox(tipos);
        comboBoxs = new HashMap<>();
        comboBoxs.put("alias",cBoxAlias);
        comboBoxs.put("bicicleta",cBoxBicicleta);
        comboBoxs.put("moto",cBoxMoto);
        comboBoxs.put("proveedor",cBoxProveedor);
        comboBoxs.put("tipo",cBoxTipo);
        comboBoxs.put("fabricante",cBoxFabricante);
    }

    //se usa para consulta de datos en la BD. crea el texto con el nombre de las columnas separadas por comas para realizar el read.
    private String arregloSeparator(String[] columnasSQL) {
        String salida = "";
        for (int i = 0; i < columnasSQL.length; i++) {
            salida += (i != columnasSQL.length-1)?columnasSQL[i] + ",":columnasSQL[i];
        }
        return salida;
    }
    private String arregloSeparator(String[] columnasSQL, int colPK) {
        String salida = "";
        for (int i = 0; i < columnasSQL.length; i++) {
            if(i != colPK){
                salida += (i != columnasSQL.length-1)?columnasSQL[i] + ",":columnasSQL[i];
            }
        }
        return (columnasSQL.length-1 == colPK)?salida.substring(0,salida.length()-1):salida;
    }

    //abre una ventana que imprime los valores de dicha entidad.
    protected void imprimir() {
        imprimir.desplegarForm(encabezadosEntidad, datosEntidad);
    }

    //pone la tabla y el form por dafault
    protected void setDefault() {
        cBoxEntidad.setSelectedIndex(0);
        cargarTabla();
    }

    //funcion que despliega la ventana y sus elementos.
    public void desplegarForm() {
        desplegar = true;
        setContentPane(JpanelTablas);
        setTitle("TABLAS DE CONSULTAS");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        comboBoxOperations(cBoxEntidad,entidades);
        cargarBarraMenu();
        cargarTabla();
    }

    //inicializa las opciones de la barra de menu superior
    private void cargarBarraMenu() {
        setLayout(null);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //creacion de las opciones en la barra menu
        menu1 = new JMenu("ARCHIVO");
        menu2 = new JMenu("ALTERNATIVA_2");
        menuBar.add(menu1);
        menuBar.add(menu2);
        
        //creacion de las opciones de cada opcion de la barra menu
        mi11 = new JMenuItem("GUARDAR TODO");
        mi12 = new JMenuItem("CARGAR DE NUEVO");
        mi13 = new JMenuItem("IMPRIMIR");
        mi21 = new JMenuItem("OPCION ALTERNATIVA");

        mi11.addActionListener(this);
        mi12.addActionListener(this);
        mi13.addActionListener(this);
        mi21.addActionListener(this);

        menu1.add(mi11);
        menu1.add(mi12);
        menu1.add(mi13);
        menu2.add(mi21);
    }

    //funcion que se usa para llenar cualquier combobox
    public void comboBoxOperations(JComboBox comboBox, String[] lista){
        for (String item: lista) comboBox.addItem(item);
    }

}