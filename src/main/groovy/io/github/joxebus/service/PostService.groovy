package io.github.joxebus.service

import groovy.text.SimpleTemplateEngine
import groovy.transform.CompileStatic
import groovy.yaml.YamlSlurper
import io.github.joxebus.conf.Configuration

@CompileStatic
class PostService {

    def newPost(String title) {
        def now = new Date().format(Configuration.dateFormat)
        String blogTitle = [now, title.toLowerCase().replace(" ", "-")].join("-")
        File configFile = new File(Configuration.BLOG_FOLDER+File.separator+"_posts", "${blogTitle}.md")
        def toolSettings = new YamlSlurper().parse(new File(Configuration.HOME_FOLDER, "settings.yml"))
        def binding = [
                title: title,
                date: new Date().format(Configuration.dateFormatDetail),
                author: toolSettings["author"],
                tags: toolSettings["tags"]
        ]
        def engine = new SimpleTemplateEngine()
        configFile.text = engine.createTemplate(Configuration.blogPostTemplate).make(binding)
        println "New post created at [${configFile.absolutePath}]"
        if(toolSettings["open_on_create"] == "y") {
            println "Opening file with [${toolSettings["editor"]}]"

            String command = "${toolSettings["editor"]} ${configFile.absolutePath}"
            command.execute()
        }

    }

    def deletePost(String filename) {
        File file = new File(Configuration.BLOG_FOLDER+File.separator+"_posts", filename)

        if(file.exists()) {
            file.delete()
            println "File [$filename] has been deleted."
        } else {
            println "File [$filename] doesn't exists. Please select a valid name."
            listPosts()
        }

    }

    def listPosts() {
        File directory = new File(Configuration.BLOG_FOLDER+File.separator+"_posts")
        String regex = /.*\.md/
        List<String> filenames = []

        directory.eachFileMatch(~regex) { file ->
            filenames << file.name
        }

        filenames.sort().each { println it }
    }

}
