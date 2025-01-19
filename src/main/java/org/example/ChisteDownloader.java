package org.example;

public class ChisteDownloader implements Runnable{
    private long tiempoEspera=550;
    private static ChisteDownloader instance;
    private final static int MAX_CHISTES=100;
    private ChisteDAO chisteDAO;
    private int numChistes=MAX_CHISTES;

    private ChisteDownloader(){
        chisteDAO=new ChisteDAO();
    }

    public static ChisteDownloader getInstance(){
        if (instance==null){
            synchronized (ChisteDownloader.class){
                if (instance==null){
                    instance= new ChisteDownloader();
                }
            }
        }
        return instance;
    }

    public void setNumChistes(int num){
        this.numChistes=num;
    }

    @Override
    public void run() {
        var enf = ChisteJpaManager.getEntityManagerFactory(ChisteJpaManager.CHISTE_H2);
        var en = enf.createEntityManager();

        for (int i =0; i<numChistes; i++){
            Chiste temp = chisteDAO.getJokeById(i);
            if (temp!=null) {
                en.getTransaction().begin();
                en.persist(temp);
                en.getTransaction().commit();
            }
            try {
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
