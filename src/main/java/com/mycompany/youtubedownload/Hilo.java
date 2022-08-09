package com.mycompany.youtubedownload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo implements Runnable {
    
    private final InputStream is;
    private final OutputStream os;

    public Hilo(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }

    @Override
    public void run() {
        
        final byte[] b = new byte[1024];
        try {
            for(int i = 0; (i = is.read(b)) != -1;){
                os.write(b, 0, i);
            }
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
