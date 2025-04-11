## How you approached the task.
My only experience with mobile development recently has been with Flutter, 
so this was the first time working with Kotlin/Android Studio ecosystem. I had a quick look at the Kotlin documentation and some Google Codelab projects to get an idea of the language and API features. 

Using Jetpack Compose for designing the actual UI was very intuitive and natural for me, since it's quite similar to Flutter, React, Fusion (on Roblox) which I have experience with. The UI widgets were designed with modularity in mind so I tried to break them down into extensible and reusable components.

I used Retrofit and Moshi for fetching and streamlining the JSON parsing process. I started off by defining the actual data model for a Website and a sealed interface to encapsulate the state management for loading, success state and error handling. I am a fan of type-driven development so even when I am developing my games with TypeScript, I initially create these low level building blocks to ensure consistency throughout all my code.

## Anything you’re particularly proud of.

I am proud of the fact that I was able to create a fully functional app in a totally new language and ecosystem in a pretty short amount of time (also quite often when the code compiled on the first build, that was the cherry on top). I am also happy with the error handling mechanisms I managed to add.

## What you would improve or add if you had more time.

The UI could definitely be made to look more modern and polished, and a proper design system for that could be standardised, as currently I used quite arbitrary values for padding, margins and colors.