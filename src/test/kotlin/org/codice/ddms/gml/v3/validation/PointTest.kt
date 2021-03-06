/*
Copyright (c) 2019 Codice Foundation
Released under the GNU Lesser General Public License; see
http://www.gnu.org/licenses/lgpl.html
*/
package org.codice.ddms.gml.v3.validation

import org.codice.ddms.gml.v3.Point
import org.codice.ddms.gml.v3.Position
import org.codice.ddms.gml.v3.SrsAttributes
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PointTest {
    private val position = Position(listOf(0.0, 0.0))
    private val srsAttributes = SrsAttributes("wgs84")

    @Test(expected = IllegalArgumentException::class)
    fun `a Point with no id is invalid`() {
        Point("", position, srsAttributes)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `a Point with no srsName is invalid`() {
        Point("id", position, SrsAttributes())
    }

    @Test
    fun `a Point with an id and srsName is valid`() {
        Point("id", position, srsAttributes)
    }

    @Test
    fun `a valid Point creates correct xml`() {
        val xml = """<gml:Point gml:id="id" srsName="wgs84" xmlns:gml="http://www.opengis.net/gml">
                    |<gml:pos xmlns:gml="http://www.opengis.net/gml">0.0 0.0</gml:pos>
                    |</gml:Point>""".trimMargin().replace("\n", "")
        val point = Point("id", position, srsAttributes)

        assertThat(point.toString(), equalTo(xml))
    }
}
