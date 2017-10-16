Overview
========

Langcat is a document language classifier based on ngram document distribution.  It
takes a given set of documents, parses them into profiles/categories, and then
allows the developer to classify language based on the input text.

This work is based on a 1994 paper by Cavnar and Trenkle entitled N-Gram Base
Text Categorization.

We've extended this paper to support unicode and asian languages including
Farsi, Arabic, Chinese, Korean, Chinese, and Japanese.

## How does it work? 

The library bootstraps itself with a set of known profiles.  These are then
encoded internally and then developers call Categorizer.match() which returns a
Profile.  The name of this profile is then an ISO639 language code (en, es, pt,
sv, etc).

## Comparison to other languages 

Langcat is superior to many (all) known language classification libraries in that it:

 - supports more language (272)
 - supports language siblings (dialects)
 - is extremely fast
 - handles out of memory issues, etc.

## How do you handle Korean, Chinese, and Japanese?

Korean, Chinese, and Japanese use 1 char words.  We encode these as 1 char
ngrams which our categorizer is able to handle without a problem.

## Performance 

The system is fairly quick.  On a Pentium 4M 1.7Ghz machine its able to process
1 100 char profile every 4ms.  If you have millions of documents though this
might take a long time though.

Memory use is also pretty small.  It takes about 10M of system memory to index
all profiles.

## Accuracy 

The classifier is very accurate.  It also won't return false positives.  If its
not able to categorize a document without a shadow of a doubt it will throw a
CategorizerFailedException. 

 * 25 char documents - 77%
 * 50 char documents - 93%
 * 100 char documents - 97%
 * 200 char documents - 99.705%
