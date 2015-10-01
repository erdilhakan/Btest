/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package btest;

/**
 *
 * @author Lenovo
 */
public class Path {
    public static Path instance=new Path();
    public String getPath(){
        String path = getClass().getClassLoader().getResource("keystore").getPath();
        return path;
    }
}
