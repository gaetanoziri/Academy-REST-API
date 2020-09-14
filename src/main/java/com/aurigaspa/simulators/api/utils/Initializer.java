package com.aurigaspa.simulators.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.aurigaspa.basewebapp.util.ParametersReader;
import com.aurigaspa.basewebapp.util.Properties;

public class Initializer {

    private Initializer() {
        /*
         * helper class, no need to instantiate it
         */
    }

    private static Logger logger;

    private static File confFile;

    /**
     * <p>boolean value used to ensure that initialize method is called once</p>
     */
    private static boolean isInitialized = false;

    /**
     * <p>configure log4j appenders reading configurated appenders and reads configuration file</p>
     * <p>Configurations will be readed from file pointing at "configfile.path" folder </p>
     * <p>For log4j will be looked at beSimulator.log4j.xml file</p>
     * <p>For be simulator parametersReader configuration will be looked at beSimulator.conf file</p>
     *
     */
    public static void initialize() {
        if (!isInitialized) {
            /*
            * lettura e configurazione degli appender alla prima referenza della classe Finder da parte del simulatore
            */
            String configFilePath = retrieveConfigFilePath(Arrays.asList("tcr.extconfigfile.path", "extconfigfile.path", "configfile.path"));
            String log4jPath = configFilePath + "beSimulator.log4j.xml";
            String confFilePath = configFilePath + "beSimulator.conf";
            System.out.println("BeSimulator conf file path: " + confFilePath);
            System.out.println("BeSimulator log4j file path: " + log4jPath);
            File log4jConfiguration = new File(log4jPath);
            confFile = new File(confFilePath);
            if (log4jConfiguration.exists()) {
                /*
                 * aggiorna gli appender con quanto specificato nel descrittore di log4j ogni minuto
                 */
                DOMConfigurator.configureAndWatch(log4jPath, 60000L);
                logger = Logger.getRootLogger();
                logger.info("log4j configured correctly");
            } else {
                /*
                 * non ho trovato un descrittore nella cartella di configurazione dell'ambiente corrente
                 * imposto il logging nella system.out
                 */
                BasicConfigurator.configure();
                logger = Logger.getRootLogger();
                logger.error("log4j configuration file not found at path: " + log4jConfiguration + ", logging to System.out");
            }
            isInitialized = true;
        }

    }

    /**<b>Description:</b><br>
     * Tells if BE Simulators' {@link Initializer} has been initialized or not.
     *
     * @return True if BE Simulators' {@link Initializer} has been initialized, false otherwise.
     */
    public static boolean isInitialized() {
        return isInitialized;
    }

    /**
     * <p>This method tries to retrieve property value.</p>
     * <p>Configuration file path system property value will be retrieved against passed system property labels list</p>
     * <p>Search will be blocked at the first founded system property</p>
     *
     * @param configPathSystemProperties
     * @return
     */
    private static String retrieveConfigFilePath(List<String> configPathSystemProperties) {
        String configFilePath = null;
        boolean found = false;
        Iterator<String> iterator = configPathSystemProperties.iterator();
        while (!found && iterator.hasNext()) {
            configFilePath = System.getProperty(iterator.next());
            if (configFilePath != null) {
                found = true;
                /*
                 * concatena alla system properties del path il separatore nel caso non 
                 * sia contenuto nel valore della system property stessa
                 */
                if (!configFilePath.endsWith("\\") && !configFilePath.endsWith("/")) {
                    configFilePath = configFilePath.concat(File.separator);
                }
            }
        }
        return configFilePath;
    }

    /**
     * Return parametersReader value or reference.
     *
     * @return parametersReader value or reference.
     */
    public static ParametersReader getParametersReader() {
        initialize();
        Properties properties = new Properties();
        if (confFile != null && confFile.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(confFile);
                properties.load(fis);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return new ParametersReader(properties);
    }
}
