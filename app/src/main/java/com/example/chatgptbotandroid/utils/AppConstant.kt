package com.example.chatgptbotandroid.utils

import com.example.chatgptbotandroid.data.model.AiCategoryModel

object AppConstant {

    const val API_KEY = "sk-mUeoqm6jnpUOb2DYScDpT3BlbkFJxBH4seumKa5NBJaNPdrchwo"
    const val BASE_URL = "https://api.openai.com/"
    const val MODEL_KEY = "model_key"

    val aiCategory = arrayListOf(
        AiCategoryModel(
            "1. Education",
            "You can ask ChatGPT questions related to concept explanation, problem-solving, study techniques, app recommendations, and motivation in the education sector.",
            listOf(
                "What are some good online resources for learning a new language?",
                "Can you recommend a good study technique for exams?",
                "How do I write a research paper?",
                "Can you help me choose a college major?",
                "What are some good tools for online learning?",
            )
        ),
        AiCategoryModel(
            "2. Healthcare",
            "You can ask ChatGPT questions related to symptoms, diagnoses, treatment options, medication side effects, and medical advice in the healthcare sector.",
            listOf(
                "What are the symptoms of a common cold?",
                "How do I manage chronic pain?",
                "Can you recommend any natural remedies for anxiety?",
                "What are the side effects of a particular medication?",
                "How can I stay healthy during flu season?",
            )
        ),
        AiCategoryModel(
            "3. Finance",
            "You can ask ChatGPT questions related to budgeting, investments, taxes, loans, and financial planning in the finance sector.",
            listOf(
                "How do I create a budget and stick to it?",
                "Can you recommend a good savings account?",
                "How do I invest in stocks?",
                "What is a credit score and how is it calculated?",
                "What are some good ways to save for retirement?",
            )
        ),
        AiCategoryModel(
            "4. Retail",
            "You can ask ChatGPT questions related to product recommendations, order status, return policies, loyalty programs, and store locations in the retail sector.",
            listOf(
                "How do I track my online order?",
                "Can you recommend a good product for dry skin?",
                "What is your return policy?",
                "How do I redeem a discount code?",
                "What are some good gift ideas for a special occasion?",
            )
        ),
        AiCategoryModel(
            "5. Entertainment",
            "You can ask ChatGPT questions related to movie or TV show recommendations, reviews, ratings, celebrity news, and music in the entertainment sector.",
            listOf(
                "Can you recommend a good movie to watch on a rainy day?",
                "What are some popular TV shows right now?",
                "How do I download a particular song?",
                "What are some good books to read this year?",
                "Can you recommend a good podcast to listen to?",
            )
        ),
        AiCategoryModel(
            "6. Food and beverage",
            "You can ask ChatGPT questions related to recipe recommendations, cooking techniques, restaurant suggestions, nutrition facts, and dietary restrictions in the food and beverage sector.",
            listOf(
                "What are some good vegetarian recipes?",
                "Can you recommend a good wine to pair with seafood?",
                "How do I make a delicious pizza at home?",
                "What are some good meal prep ideas for the week?",
                "Can you recommend a good restaurant for a date night?",
            )
        ),
        AiCategoryModel(
            "7. Automotive",
            "You can ask ChatGPT questions related to car models, maintenance, repair, parts, and pricing in the automotive sector.",
            listOf(
                "How do I change a tire?",
                "Can you recommend a good car insurance provider?",
                "What is the difference between all-wheel drive and four-wheel drive?",
                "What are some good winter driving tips?",
                "How do I check my car's oil level?",
            )
        ),
        AiCategoryModel(
            "8. Technology",
            "You can ask ChatGPT questions related to tech products, troubleshooting, specs, cybersecurity, and new releases in the technology sector.",
            listOf(
                "How do I troubleshoot a computer issue?",
                "Can you recommend a good antivirus software?",
                "What is the best way to protect my data online?",
                "How do I set up a new wireless router?",
                "What are some good productivity apps to use?",
            )
        ),
        AiCategoryModel(
            "9. Sports and fitness",
            "You can ask ChatGPT questions related to fitness routines, workout plans, equipment recommendations, sports news, and athlete profiles in the sports and fitness sector.",
            listOf(
                "How do I stretch before a workout?",
                "Can you recommend a good workout routine for beginners?",
                "What are some good ways to stay hydrated during exercise?",
                "What are the benefits of meditation for athletes?",
                "Can you recommend a good fitness tracker?",
            )
        ),
        AiCategoryModel(
            "10. Health and wellness",
            "You can ask ChatGPT questions related to disease symptoms, diagnosis, treatment, healthy habits, and lifestyle choices in the health and wellness sector.",
            listOf(
                "What are some good ways to manage stress and anxiety?",
                "Can you recommend any natural remedies for common health issues?",
                "How can I maintain a healthy diet while still enjoying my favorite foods?",
                "What are some good exercises for beginners?",
                "How can I improve my sleep quality?",
            )
        ),
        AiCategoryModel(
            "11. Travel and tourism",
            "You can ask ChatGPT questions related to destination recommendations, travel planning, accommodations, activities, and cultural experiences in the travel and tourism sector.",
            listOf(
                "Can you recommend a good destination for a family vacation?",
                "What are the best ways to save money on travel?",
                "Can you help me plan a trip itinerary?",
                "What are some good cultural experiences to have while traveling?",
                "What are the best ways to travel sustainably?",
            )
        ),
        AiCategoryModel(
            "12. Home and garden",
            "You can ask ChatGPT questions related to home improvement, gardening, decor, organization, and cleaning tips in the home and garden sector.",
            listOf(
                "How do I decorate my home on a budget?",
                "Can you recommend any good home improvement projects?",
                "What are some good ways to organize my home?",
                "Can you help me choose the right furniture for my space?",
                "What are some good gardening tips for beginners?",
            )
        ),
        AiCategoryModel(
            "13. Fashion and Beauty",
            "In the fashion and beauty sector, you can ask ChatGPT about fashion trends, beauty tips, product recommendations, styling advice, and more.",
            listOf(
                "What are some popular fashion trends this season?",
                "How can I style my hair for a formal event?",
                "Can you recommend a good facial cleanser for sensitive skin?",
                "What is the difference between matte and glossy lipstick?",
                "What are some popular nail polish colors for summer?",
            )
        ),
        AiCategoryModel(
            "14. Real estate",
            "You can ask ChatGPT about real estate trends, property valuation, legal procedures, mortgage options, and market analysis.",
            listOf(
                "What is the average cost per square foot for a home in [specific location]?",
                "What are the current mortgage interest rates for a first-time homebuyer?",
                "Can you recommend some reputable real estate agents in [specific area]?",
                "What are the best neighborhoods to invest in for rental properties in [specific city]?",
                "What is the process for buying a foreclosed property, and are there any risks involved?",
            )
        ),
        AiCategoryModel(
            "15. Marketing and advertising",
            "You can ask ChatGPT about advertising strategies, target audiences, branding, market research, and social media engagement.",
            listOf(
                "What are some effective ways to increase website traffic through online advertising?",
                "Can you suggest some strategies to improve engagement on social media platforms?",
                "How can businesses measure the success of their marketing campaigns?",
                "What are some best practices for creating compelling email marketing campaigns?",
                "Can you recommend some tools to analyze competitors' advertising strategies and improve our own campaigns?",
            )
        ),
        AiCategoryModel(
            "16. Human resources",
            "You can ask ChatGPT about HR policies, job openings, employee benefits, performance management, and career development opportunities.",
            listOf(
                "How can I improve employee engagement and motivation in the workplace?",
                "What are some effective strategies for recruiting top talent?",
                "How can I ensure that my company is in compliance with labor laws and regulations?",
                "What are some best practices for managing and resolving conflicts between employees?",
                "How can I develop an effective performance evaluation system for my employees?",
            )
        ),
        AiCategoryModel(
            "17. Sports and Fitness",
            "You can ask ChatGPT about fitness tips, workout routines, nutrition advice, sports events, and sports equipment recommendations in the Sports and Fitness sector.",
            listOf(
                "How can we increase our donations and funding for our non-profit organization?",
                "What are some effective ways to engage volunteers and keep them motivated?",
                "How can we raise awareness about our cause and reach a wider audience?",
                "What are the most successful fundraising campaigns that non-profits have used in the past?",
                "How can we measure the impact and effectiveness of our non-profit programs and initiatives?",
            )
        ),
        AiCategoryModel(
            "18. News and Current Events",
            "You can ask ChatGPT for the latest news updates, trending topics, fact-checking, and analysis on current events in 20 words.",
            listOf(
                "What are the latest developments in the COVID-19 pandemic?",
                "Can you explain the recent political situation in Syria?",
                "What are the major headlines in today's news?",
                "How has the stock market been performing lately?",
                "What is the latest technology innovation?",
            )
        ),
        AiCategoryModel(
            "19. Non-profit and Charity",
            "Some examples of questions to ask ChatGPT in the \"Non-profit and Charity\" sector could be about fundraising, volunteer opportunities, impact measurement, and donation management.",
            listOf(
                "How can we increase our donations and funding for our non-profit organization?",
                "What are some effective ways to engage volunteers and keep them motivated?",
                "How can we raise awareness about our cause and reach a wider audience?",
                "What are the most successful fundraising campaigns that non-profits have used in the past?",
                "How can we measure the impact and effectiveness of our non-profit programs and initiatives?",
            )
        ),
        AiCategoryModel(
            "20. Gaming",
            "You can ask ChatGPT about game strategies, tips, reviews, or recommendations for different gaming platforms and genres.",
            listOf(
                "What are some popular TV shows right now?",
                "Can you recommend a good movie to watch this weekend?",
                "How can I find out about upcoming concerts in the area?",
                "What are some good books to read this summer?",
                "What is the difference between modern and contemporary art?",
            )
        ),
    )
}
