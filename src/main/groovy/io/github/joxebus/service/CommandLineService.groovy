package io.github.joxebus.service

import groovy.transform.CompileStatic

@CompileStatic
class CommandLineService {

    ConfigurationService configurationService = new ConfigurationService()
    PostService postService = new PostService()

    void configureSettings() {
        configurationService.configureSettings()
    }

    void configure() {
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
