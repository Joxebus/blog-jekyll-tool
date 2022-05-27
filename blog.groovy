#!/usr/bin/env groovy

/**
 * Installation:
 * chmod +x blog.groovy
 * cp blog.groovy /usr/local/bin/blog
 */

import groovy.yaml.YamlSlurper
import groovy.yaml.YamlBuilder

// The location of your configuration for Jekill blog
String HOME_FOLDER = System.getProperty("user.home") + File.separator + "blog-config"
String BLOG_FOLDER = System.getenv("BLOG_FOLDER")
File homeFolder = new File(HOME_FOLDER)
List<String> configurations = ["title", "author", "description", "email", "twitter_username", "github_username", "linkedin_username"]
// Read the input from console
String[] args = getProperty("args") as String[]

def usage = { ->
    println """
usage: blog -[chnl]
 -c,--configure     Configure user info
 -h,--help          Usage Information
 -n,--new           Create new blog post
 -l,--list          List blog posts created
"""
}

def configure = { ->
    File configFileBackup = new File(BLOG_FOLDER, "_config.bak")
    File configFile = new File(BLOG_FOLDER, "_config.yml")

    if(!configFileBackup.exists()) {
        configFileBackup << configFile.text
    }

    YamlSlurper yamlSlurper = new YamlSlurper()
    YamlBuilder yamlBuilder = new YamlBuilder()
    def configYaml = yamlSlurper.parse(configFile)
    configurations.each {configuration ->
        print "Enter a value for [$configuration]: "
        String answer = System.in.newReader().readLine()
        if(answer) {
            configYaml[configuration] = answer
        }

    }
    yamlBuilder(configYaml)

    configFile.text = yamlBuilder.toString()
    println "Configuration finished, please verify your configuration on [${configFile.absolutePath}]"
}

if (!args || args.length > 2) {
    println "Invalid arguments, please see the usage section: "
    usage()
    return 1
}

if(!BLOG_FOLDER) {
    println "You must setup your env var BLOG_FOLDER first"
    return 1
}


if(!homeFolder.exists()) {
    println "Creating home folder [${HOME_FOLDER}]"
    homeFolder.mkdirs()
}

if (["-h", "--help"].contains(args[0])) {
    usage()
    return
}

if(["-c", "--configure"].contains(args[0])) {
    configure()
    return
}

