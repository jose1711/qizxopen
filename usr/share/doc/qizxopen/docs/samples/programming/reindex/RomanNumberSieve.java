/*
 *    Qizx/open 4.1
 *
 * This code is part of the Qizx application components
 * Copyright (C) 2004-2010 Axyana Software -- All rights reserved.
 *
 * The contents of this file are subject to the Mozilla Public License 
 *  Version 1.1 (the "License"); you may not use this file except in 
 *  compliance with the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 * The Initial Developer of the Original Code is Xavier Franc - Axyana Software.
 *
 */
/*
 *    Qizx Free_Engine-4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx Free_Engine-4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx Free_Engine-4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx Free_Engine-4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx 4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx 4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx 4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx 4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx 4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
/*
 *    Qizx 4.0
 *
 *    This code is part of the Qizx application components
 *    Copyright (c) 2004-2010 Axyana Software -- All rights reserved.
 *
 *    For conditions of use, see the accompanying license files.
 */
package reindex;

import com.qizx.api.Indexing;

public final class RomanNumberSieve implements Indexing.NumberSieve {
    private static final class Symbol {
        public final char symbol;
        public final int value;

        public Symbol(char symbol, int value) {
            this.symbol = symbol;
            this.value = value;
        }
    }

    private static final Symbol[] SYMBOLS = {
        new Symbol('M', 1000),
        new Symbol('D', 500),
        new Symbol('C', 100),
        new Symbol('L', 50),
        new Symbol('X', 10),
        new Symbol('V', 5),
        new Symbol('I', 1)
    };

    // -----------------------------------------------------------------------

    public double convert(String text) {
        double converted = 0;

        char[] chars = text.trim().toUpperCase().toCharArray();
        int maxSymbolValue = -1;

        for (int j = chars.length-1; j >= 0; --j) {
            char c = chars[j];

            Symbol symbol = null;
            for (int i = 0; i < SYMBOLS.length; ++i) {
                if (SYMBOLS[i].symbol == c) {
                    symbol = SYMBOLS[i];
                    break;
                }
            }
            if (symbol == null) {
                return Double.NaN;
            }

            if (symbol.value >= maxSymbolValue) {
                // Example: second "M" in "MCMXC" (1990).
                maxSymbolValue = symbol.value;
                converted += maxSymbolValue;
            } else {
                // Example: first "C" in "MCMXC" (1990).
                converted -= symbol.value;
            }
        }

        return converted;
    }

    public void setParameters(String[] parameters) {}
    public String[] getParameters() { return null; }

    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        // Examples: MCMLXX = 1970, MCMLVII = 1957
        RomanNumberSieve converter = new RomanNumberSieve();
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            System.out.println(arg + " = " + converter.convert(arg));
        }
    }
}
