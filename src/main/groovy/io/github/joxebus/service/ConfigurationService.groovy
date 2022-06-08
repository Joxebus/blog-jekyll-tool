package io.github.joxebus.service

import groovy.transform.CompileStatic
import groovy.yaml.YamlBuilder
import groovy.yaml.YamlSlurper
import io.github.joxebus.conf.Configuration

@CompileStatic
class ConfigurationService {

    def configureSettings() {
        def yamlBuilder = new YamlBuilder()
        File settingsFile = new File(Configuration.HOME_FOLDER, Configuration.SETTINGS_FILE_NAME)

        Map settingsMap = [:]
        Configuration.settings.each { setting, message ->
            print message
            String answer = System.in.newReader().readLine()
            settingsMap[setting] = answer
        }
        yamlBuilder(settingsMap)

        settingsFile.text = yamlBuilder
    }

    def configure() {
        File configFileBackup = new File(Configuration.BLOG_FOLDER, Configuration.CONFIG_BACKUP_FILE_NAME)
        File configFile = new File(Configuration.BLOG_FOLDER, Configuration.CONFIG_BACKUP_FILE_NAME)

        if(!configFileBackup.exists()) {
            configFileBackup << configFile.text
        }

        YamlBuilder yamlBuilder = new YamlBuilder()
        def configYaml = new YamlSlurper().parse(configFile)
        Configuration.configurations.each {configuration ->
            print "Enter a value for [$configuration]: "
            String answer = System.in.newReader().readLine()
            if(answer) {
                configYaml[configuration] = answer
            }

        }
        yamlBuilder(configYaml)

        configFile.text = yamlBuilder
        println "Configuration finished, please verify your configuration on [${configFile.absolutePath}]"
    }
}
