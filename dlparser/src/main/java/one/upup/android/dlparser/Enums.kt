package one.upup.android.dlparser

/**
AAMVA Issuing Countries

- UnitedStates: The USA
- Canda: Canada, eh?
- Unknown: When the issuing country is not available
 */
public enum class IssuingCountry{
    // The United States
     UnitedStates,

    // Canada
     Canada,

    // Unknown Issuing Country
     Unknown
}

/**
AAMVA Genders

- Male: Male
- Female: Female
- Other: Not yet part of the AAMVA spec
- Unknown: When the gender cannot be determined
 */
public enum class Gender{

    // Male
     Male,

    // Female
     Female,

    // Other
     Other,

    // Unknown Gender
     Unknown
}

/**
AAMVA Eye Colors

- Black: Black eyes
- Blue: Blue eyes
- Brown: Brown eyes
- Gray: Gray eyes
- Green: Green eyes
- Hazel: Hazel eyes
- Maroon: Maroon eyes
- Pink: Pink eyes
- Dichromatic: Dichromatic eyes
- Unknown: Unknown eye color
 */
public enum class EyeColor{
    // Black eye color
     Black,
    // Blue eye color
     Blue,
    // Brown eye color
     Brown,
    // Gray eye color
     Gray,
    // Green eye color
     Green,
    // Hazel eye color
     Hazel,
    // Maroon eye color
     Maroon,
    // Pink eye color
     Pink,
    // Dichromatic eye color
     Dichromatic,
    // Unknown eye color
     Unknown
}

/**
AAMVA hair colors

- Bald: Bald hair
- Black: Black hair
- Blond: Blond hair
- Brown: Brown hair
- Grey: Grey hair
- Red: Red hair
- Sandy: Sandy hair
- White: White hair
- Unknown: Unknown hair color
 */
public enum class HairColor{
    // Bald hair color
     Bald,
    // Black hair color
     Black,
    // Blond hair color
     Blond,
    // Brown hair color
     Brown,
    // Grey hair color
     Grey,
    // Red hair color
     Red,
    // Sandy hair color
     Sandy,
    // White hair color
     White,
    // Unknown hair color
     Unknown
}

/**
AAMVA Name Truncations

- Truncated: The name was truncated
- None: The name was not truncated
- Unknown: When the truncation cannot be determined
 */
public enum class Truncation{
    // Truncated Name
     Truncated,
    // Not Truncated
     None,
    // Unknown Truncation
     Unknown
}

/**
AAMVA Name Suffixes

- Junior: Junior, Jr.
- Senior: Senior, Sr.
- First: First, I, 1st
- Second: Second, II, 2nd
- Third: Third, III, 3rd
- Fourth: Fourth, IV, 4th
- Fifth: Fifth, V, 5th
- Sixth: Sixth, VI, 6th
- Seventh: Seventh, VII, 7th
- Eighth: Eighth, VIII, 8th
- Ninth: Ninth, IX, 9th
- Unknown: When the name suffix is unknown
 */
public enum class NameSuffix{
    // Junior, Jr.
     Junior,
    // Senior, Sr.
     Senior,
    // First, I, 1st
     First,
    // Second, II, 2nd
     Second,
    // Third, III, 3rd
     Third,
    // Fourth, IV, 4th
     Fourth,
    // Fifth, V, 5th
     Fifth,
    // Sixth, VI, 6th
     Sixth,
    // Seventh, VII, 7th
     Seventh,
    // Eighth, VIII, 8th
     Eighth,
    // Ninth, IX, 9th
     Ninth,
    // When the name suffix is unknown
     Unknown
}