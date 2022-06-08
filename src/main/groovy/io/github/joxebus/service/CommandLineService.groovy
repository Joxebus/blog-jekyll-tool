package io.github.joxebus.service

import groovy.transform.CompileStatic

@CompileStatic
class CommandLineService {

    ConfigurationService configurationService = new ConfigurationService()
    PostService postService = new PostService()

    void configureSettings() {
        println("""===================================
           |Setup local settings for [blog] command
           |===================================""".stripMargin())
        configurationService.configureSettings()
    }

    void configure() {
        println("""===================================
           |Setup your blog project
           |===================================""".stripMargin())
        configurationService.configure()
    }

    void newPost(String title) {
        postService.newPost(title)
    }

    void listPosts() {
        postService.listPosts()
    }

    void delete(String filename) {
        postService.deletePost(filename)
    }
}
