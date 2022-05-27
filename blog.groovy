#!/usr/bin/env groovy

/**
 * Installation: See more on README.md
 * chmod +x blog.groovy
 * cp blog.groovy /usr/local/bin/blog
 */

import groovy.yaml.YamlSlurper
import groovy.yaml.YamlBuilder

// The location of your configuration for Jekyll blog
String HOME_FOLDER = System.getProperty("user.home") + File.separator + "blog-config"
String BLOG_FOLDER = System.getenv("BLOG_FOLDER")
File homeFolder = new File(HOME_FOLDER)
List<String> configurations = ["title", "author", "description", "email", "twitter_username", "github_username", "linkedin_username"]
String dateFormat = "yyyy-MM-dd"
// Read the input from console
String[] args = getProperty("args") as String[]

def usage = { ->
    println """
usage: blog -[chnl]
 -c,--configure     Configure user info
 -h,--help          Usage Information
 -n,--new <Title>   Create new blog post with title
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

def newPost = { String title ->
    def now = new Date().format(dateFormat)
    String blogTitle = [now, title.toLowerCase().replace(" ", "-")].join("-")
    File configFile = new File(BLOG_FOLDER+File.separator+"_posts", "${blogTitle}.md")
    configFile.text = "## $title \n\nYou can start writing here in Markdown format https://www.markdownguide.org/basic-syntax/"
    println "New post created at [${configFile.absolutePath}]"
}

def listPosts = {
    File directory = new File(BLOG_FOLDER+File.separator+"_posts")
    String regex = /.*\.md/
    List<String> filenames = []

    directory.eachFileMatch(~regex) { file ->
        filenames << file.name
    }

    filenames.sort().each { println it }
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

if(["-n", "--new"].contains(args[0])) {
    if(args.length < 2) {
        println "You must to specify a Title with the command -n or --new"
        usage()
        return 1
    }
    newPost(args[1])
    return
}

if(["-l", "--list"].contains(args[0])) {
    listPosts()
    return
}

