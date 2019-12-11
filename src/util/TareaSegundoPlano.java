package util;

import vista.DialogoCargando;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import utilidades.MetodosUtiles;

/**
 * <code>TareaSegundoPlano</code> permite definir una tarea 
 * que requiere ser cargada en otro hilo diferente al principal
 * y de esta forma evitar que se congele la aplicación.
 * Solo es necesario crear una instancia de esta clase y
 * sobreescribir el método <i>tareaHaRealizar</i>. Si por 
 * alguna excepción la tarea NO es completada correctamente, se
 * anota en un fichero .log dicha excepción.
 *
 */
public class TareaSegundoPlano {

    private Timer timer;
    private Integer cantidad;
    
    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }

    class TSP extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            try {Thread.sleep(1500);} catch (InterruptedException ie) {}
                dialogoCargando.setEstadoTarea("La tarea está siendo " +
                        "procesada...");
            try {
                tareaHaRealizar();
            } catch (RuntimeException re) {
                // cancelo la tarea
                cancel(true);
                // vaciado de la pila
                re.printStackTrace();
                // anoto la traza
                MetodosUtiles.anotarTraza("TSP",
                        re.toString() + "\r\n\t"
                        + re.getStackTrace()[0] + "\r\n\t"
                        + re.getStackTrace()[1] + "\r\n\t"
                        + re.getStackTrace()[2]);
            }
            return null;
        }

        @Override
        protected void done() {
            new Thread() {
                @Override
                public void run() {
                    dialogoCargando.getBarraProgresoTarea()
                            .setIndeterminate(false);
                    if (isCancelled()) {
                        dialogoCargando.setEstadoTarea("La tarea no se ha " +
                                "podido completar");
                    } else {
                        timer = new Timer(10, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (dialogoCargando.getBarraProgresoTarea()
                                        .getValue() < 100) {
                                    dialogoCargando.getBarraProgresoTarea()
                                            .setValue(dialogoCargando
                                            .getBarraProgresoTarea().getValue()
                                            + 5);
                                } else {
                                    timer.stop();
                                    dialogoCargando.setEstadoTarea("La tarea " +
                                            "ha finalizado correctamente");
                                }
                            }
                        });
                        timer.start();
                    }
                    try {sleep(1000);} catch (InterruptedException ie) {}
                    dialogoCargando.setModal(false);
                    dialogoCargando.setVisible(false);
                    tareaFinalizada();
                    dialogoCargando.dispose();
                    
                }
            }.start();
            
        }
    }


    private DialogoCargando dialogoCargando;
    private TSP tsp;

    /**
     *
     * @param framePrinciapal  Frame
     * @param tituloTarea  Título de la tarea que se desea llevar
     * a cabo en un segundo plano.
     */
    public TareaSegundoPlano(Window ventana, String tituloTarea) {
        dialogoCargando = new DialogoCargando(ventana, tituloTarea);
        dialogoCargando.setEstadoTarea("La tarea está por iniciarse...");
        tsp = new TSP();
    }

    /**
     * Es necesario definir dentro de este método la tarea que
     * se quiere realizar en segundo plano.
     */
    protected void tareaHaRealizar() {
            dialogoCargando.setCantidad(String.valueOf(cantidad));
    }

    /**
    * Definir dentro de este método algo que se desea
     * realizar al finalizar la tarea.
     */
    protected void tareaFinalizada() {
    }

    /**
     * Inicia la ejecución de la tarea.
     */
    public final void ejecutarTarea() {
        tsp.execute();
        dialogoCargando.setVisible(true);
    }


}
