// ============================================================================
//   Copyright 2006 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.watchmaker.examples.strings;

import java.util.Random;
import org.uncommons.watchmaker.framework.AbstractCandidateFactory;

/**
 * Generates random strings of a fixed length from a given alphabet.
 * @author Daniel Dyer
 */
public class StringFactory extends AbstractCandidateFactory<String>
{
    private final char[] alphabet;
    private final int stringLength;

    public StringFactory(char[] alphabet,
                         int stringLength)
    {
        this.alphabet = alphabet.clone();
        this.stringLength = stringLength;
    }


    protected String generateRandomCandidate(Random rng)
    {
        char[] chars = new char[stringLength];
        for (int i = 0; i < stringLength; i++)
        {
            chars[i] = alphabet[rng.nextInt(alphabet.length)];
        }
        return new String(chars);
    }
}