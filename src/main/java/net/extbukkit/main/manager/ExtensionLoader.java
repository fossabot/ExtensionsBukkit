package net.extbukkit.main.manager;

import net.extbukkit.api.extension.AExtension;
import net.extbukkit.api.loader.IExtensionLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExtensionLoader implements IExtensionLoader {
    private List<AExtension> extensions = new ArrayList<>();
    /**
     * Loads extension from file
     *
     * @param extension File containing an extension
     * @return true or false
     */
    //TODO Handle dependencies
    public boolean load(File extension) {
        if(!extension.exists()) return false;
        if(!extension.getName().endsWith(".jar")) return false;
        List<String> cls = new ArrayList<>();
        JarFile jar;
        try {
            jar = new JarFile(extension);
        } catch (IOException e) {
            return false;
        }
        Enumeration<JarEntry> jes = jar.entries();
        while(jes.hasMoreElements()) {
            JarEntry e = jes.nextElement();
            if(e.isDirectory() || !e.getName().endsWith(".class"))
                continue;
            cls.add(e.getName().substring(0, e.getName().length() - 6).replace("/", "."));
        }
        URLClassLoader cl;
        try {
            cl = URLClassLoader.newInstance(new URL[] {extension.toURI().toURL()}, getClass().getClassLoader());
        } catch (MalformedURLException e) {
            return false;
        }
        AExtension ext;
        for(String cn : cls) {
            Class<?> c;
            try {
                c = Class.forName(cn, true, cl);
            } catch (ClassNotFoundException e) {
                return false;
            }
            Class<? extends AExtension> ec;
            try {
                ec = c.asSubclass(AExtension.class);
            } catch (Exception e) {
                return false;
            }
            try {
                ext = ec.newInstance();
            } catch (InstantiationException e) {
                return false;
            } catch (IllegalAccessException e) {
                return false;
            }
            if(ext != null) {
                extensions.add(ext);
                return true;
            }
        }

        return false;

        /*JarFile jar;
        try {
            jar = new JarFile(extension);
        } catch (IOException e) {
            return false;
        }
        Enumeration<JarEntry> e = jar.entries();
        URL[] u;
        try {
            u = new URL[]{ new URL("jar:file:" + extension.getAbsolutePath() + "!/") };
        } catch (MalformedURLException e1) {
            return false;
        }
        URLClassLoader cl = URLClassLoader.newInstance(u);
        List<Class> classes = new ArrayList<>();
        while(e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class"))
                continue;
            String clazz = je.getName().substring(0, je.getName().length() - 6);
            clazz = clazz.replace('/', '.');
            Class c = null;
            try {
                c = cl.loadClass(clazz);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            classes.add(c);
        }
        for(Class cs : classes) {
            Class<? extends Extension> ext;
            try {
                ext = cs.asSubclass(Extension.class);
            }
            catch (ClassCastException cce) {
                continue;
            }
            Extension rext;
            try {
                rext = ext.newInstance();
            } catch (InstantiationException e1) {
                continue;
            } catch (IllegalAccessException e1) {
                continue;
            }
            extensions.add(rext);
        }*/
    }

    @Override
    public void loadAll(File dir) {
        if(!dir.isDirectory()) return;
        for(File f : dir.listFiles((dir1, name) -> name.endsWith(".jar")))
            load(f);
    }

    public List<AExtension> getExtensions() {
        return extensions;
    }
}