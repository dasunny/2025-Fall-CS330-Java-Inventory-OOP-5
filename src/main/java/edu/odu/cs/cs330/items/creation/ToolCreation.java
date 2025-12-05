package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Tool;

@SuppressWarnings({
    "PMD.AtLeastOneConstructor"
})
public class ToolCreation implements ItemCreationStrategy
{
    /**
     * Convenience wrapper to mirror the Rust new-returns-a-builder pattern.
     * Use "create" since "new" is a reserved keyword in Java.
     */
    public static ToolCreation construct()
    {
        return new ToolCreation();
    }

    @Override
    public Item fromDefaults()
    {
        // Use the default Tool constructor
        return new Tool();
    }

    @Override
    public int requiredNumberOfValues()
    {
        // After the "Tool" keyword, each line has:
        // name, material, durability, speed, modifier, modifier level
        return 6;
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal"
    })
    @Override
    public Item fromTokens(final String... tokens)
    {
        // tokens: [0] name, [1] material, [2] durability,
        //         [3] speed, [4] modifier, [5] modifier level
        String name       = tokens[0];
        String material   = tokens[1];
        int durability    = Integer.parseInt(tokens[2]);
        int speed         = Integer.parseInt(tokens[3]);
        String modifier   = tokens[4];
        int modifierLevel = Integer.parseInt(tokens[5]);

        return new Tool(name, durability, speed, material, modifier, modifierLevel);
    }

    @SuppressWarnings({
        "PMD.LawOfDemeter",
        "PMD.LocalVariableCouldBeFinal",
        "PMD.OnlyOneReturn"
    })
    @Override
    public Item fromExisting(final Item original)
    {
        if (!(original instanceof Tool)) {
            return null;
        }

        Tool theOriginal = (Tool) original;

        // Create a *new* Tool with the same properties (immutability style)
        return new Tool(
            theOriginal.getName(),
            theOriginal.getDurability(),
            theOriginal.getSpeed(),
            theOriginal.getMaterial(),
            theOriginal.getModifier(),
            theOriginal.getModifierLevel()
        );
    }
}
