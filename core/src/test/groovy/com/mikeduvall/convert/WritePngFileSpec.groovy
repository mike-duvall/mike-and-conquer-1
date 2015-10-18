package com.mikeduvall.convert

import spock.lang.Specification


class WritePngFileSpec extends Specification {

    def "x"() {
        given:
        WritePngFile writePngFile = new WritePngFile()

        when:
        writePngFile.write()

        then:
        true
    }

}