package io.github.javaconductor.gserv.actions

/**
 * Represents a URI/HttpMethod/Behavior Combination.  The encapsulation of a resource.
 */
class ResourceAction {

    private def _urlPatterns
    private def _queryPattern
    private def _handler, _method
    private def _options
    String name

    String toString() {
        return "$_method(/" + _urlPatterns.join("/") + ")"
    }

    def ResourceAction(method, urlPatterns, queryPattern, clHandler) {
        this(method, urlPatterns, queryPattern, [:], clHandler)
    }

    def ResourceAction(name, method, urlPatterns, ActionPathQuery queryPattern, Map options, clHandler) {
        this(method, urlPatterns, queryPattern, options, clHandler)
        this.name = name
    }

    def ResourceAction(method, urlPatterns, ActionPathQuery queryPattern, Map options, clHandler) {
        _queryPattern = queryPattern
        _urlPatterns = urlPatterns
        _handler = clHandler
        _method = method
        _options = options
    }

    //returns Closure passed to method function
    def requestHandler() {
        _handler
    }

    def method() { _method }

    Map options() { _options }
    //returns PathElement representing const or var
    def path(idx) {
        (idx >= 0 && idx < _urlPatterns.size()) ? _urlPatterns[idx] : null
    }

    //returns number of elements in path
    def pathSize() {
        _urlPatterns.size()
    }

    //returns list of elements in path
    def pathElements() {
        (_urlPatterns as List).asImmutable()
    }

    //returns number of query values
    def queryPatternSize() {
        _queryPattern.size()
    }

    ActionPathQuery queryPattern() {
        _queryPattern
    }
}
