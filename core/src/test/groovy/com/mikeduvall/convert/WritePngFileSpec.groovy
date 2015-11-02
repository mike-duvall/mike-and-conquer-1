package com.mikeduvall.convert

import spock.lang.Ignore
import spock.lang.Specification


class WritePngFileSpec extends Specification {

    @Ignore
    def "x"() {
        given:
        WritePngFile writePngFile = new WritePngFile()

        when:
        writePngFile.write()

        then:
        true
    }

}