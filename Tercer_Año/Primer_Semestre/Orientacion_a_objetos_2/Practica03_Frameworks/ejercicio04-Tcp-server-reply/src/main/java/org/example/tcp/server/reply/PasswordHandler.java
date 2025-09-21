package org.example.tcp.server.reply;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordHandler implements IMessageHandler{

    @Override
    public void handleMessage(String var1, PrintWriter var2) {
        String[] var3 = var1.split(" ");
        if (var3.length != 3) {
            System.exit(1);
        }

        var2.println(this.generatePassword(var3));
    }

    private String generatePassword(String[] var1) {
        Random var2 = new Random();
        String var3 = var1[0];
        String var4 = var1[1];
        String var5 = var1[2];
        StringBuilder var6 = (new StringBuilder()).append(var5.charAt(var2.nextInt(var5.length())))
                                                  .append(var3.charAt(var2.nextInt(var3.length())))
                                                  .append(var4.charAt(var2.nextInt(var4.length())));
        String var7 = var3 + var4;

        for(int var8 = 0; var8 < 5; ++var8) {
            var6.append(var7.charAt(var2.nextInt(var7.length())));
        }

        List var12 = Arrays.asList(var6.toString().split(""));
        Collections.shuffle(var12);
        StringBuilder var9 = new StringBuilder();

        for(String var11 : var12) {
            var9.append(var11);
        }

        return var9.toString();
    }
}
