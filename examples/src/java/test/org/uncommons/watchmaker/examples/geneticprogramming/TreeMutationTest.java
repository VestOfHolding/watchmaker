// ============================================================================
//   Copyright 2006, 2007 Daniel W. Dyer
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
package org.uncommons.watchmaker.examples.geneticprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

/**
 * Unit test for the {@link TreeMutation} evolutionary operator.
 * @author Daniel Dyer
 */
public class TreeMutationTest
{
    /**
     * If the mutation doesn't happen, the candidates should be returned unaltered. 
     */
    @Test
    public void testNoMutations()
    {
        EvolutionaryOperator<Node> mutation = new TreeMutation(new TreeFactory(0, 3, 0.5, 0.5),
                                                               0); // Zero probability means no mutations.
        List<Node> candidates = Arrays.<Node>asList(new Addition(new Constant(3), new Constant(4)));
        List<Node> result = mutation.apply(candidates, new MersenneTwisterRNG());
        assert result.size() == 1 : "Wrong number of trees returned: " + result.size();
        assert candidates.get(0) == result.get(0) : "Tree should have been returned unmodified.";
    }


    /**
     * If the mutation doesn't happen, the candidates should be returned unaltered.
     */
    @Test
    public void testSomeMutations()
    {
        TreeFactory treeFactory = new TreeFactory(1, 4, 0.6, 0.2);
        EvolutionaryOperator<Node> mutation = new TreeMutation(treeFactory,
                                                               1); // Probability of 1 means guaranteed mutations.
        List<Node> candidates = treeFactory.generateInitialPopulation(20, new MersenneTwisterRNG());

        Set<Node> distinctTrees = new HashSet<Node>(candidates);

        List<Node> result = mutation.apply(candidates, new MersenneTwisterRNG());
        assert result.size() == 20 : "Wrong number of trees returned: " + result.size();

        distinctTrees.addAll(result);
        // If none of the original trees are returned, we should have 40 distict trees.
        assert distinctTrees.size() == 40 : "New trees should have been returned.";
    }
}
