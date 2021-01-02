<template>
  <div class="container">
    <header class="jumbotron">
      <div class="title">
      <h1 >BAR Logistics</h1>
      </div>
      <div class="title2">
        <h2><i>AUTOMOTIVE PARTS SHIPPING & LOGISTICS</i></h2>
      </div>

    </header>
      <div class="mainText">
        <i style="font-size:22px">With countless companies to choose from when it comes to sourcing auto parts, a
          dependable and timely inventory of parts and supplies is critical to
          preventing customers from seeking alternative options, especially when they are in desperate
          need of parts to keep their vehicles moving. <br /> <b>BAR Logistics</b> specializes in distribution in
          Bulgaria and all over the world for car part delivery companies and other businesses in
          the automotive industry.</i>
      </div>
    <img :src="image" alt="image"/>

    <br /><br /><br />
    <div class="textImage">
    <div class="textParts">
    <img :src="partsImage" width="500" alt="image"/>
    </div>
    <div class="textParts" style="font-size:19px">
      <p><b>BAR Logistics</b> delivers products including, but not limited to, car accessories, parts, tires, rims, alloys,
        wheels, car fenders, spoilers, etc. Our auto parts delivery network allows shippers to get products
        from their distribution center or mega stores to their surrounding regional stores, and/or repair
        locations securely and quickly, minimizing vehicle downtime as well as the opportunity to consider
        alternative suppliers. Our same day and next day auto parts courier services ensure that you have
        parts in your hand when you need them. We operate 24 hours a day, 365 days a year, always on a schedule
        that fits your needs.</p>
    </div>
    </div>
    <br />
    <br />
    <h2 class="subtitle">YOUR TRUSTED PARTNER <br/> FOR AUTO PARTS DELIVERY</h2>
    <div class="text2"><b>BAR Logistics</b> would love to serve as your distribution logistics partner, creating
      a more efficient and cost effective auto parts delivery solution that will mean more
      business and more profit for your company. <b>BAR Logistics</b> offers comprehensive service parts
      logistics solutions designed to minimize your involvement in managing transportation and
      car parts delivery issues.</div>

    <div class="banner">
      <img :src="homeParts" width="500" alt="image"/>
    </div>
    <p class="t">This includes:</p>
    <div class="bullets">
      <ul class="bullets">
      <li>Consistent pickup and delivery schedules custom-tailored to meet your needs</li>
      <li>Dedicated drivers that follow a strict documented procedure to ensure consistent and quality services</li>
      <li>National auto parts delivery and courier network to service any size customer</li>
      <li>Technology to maximize supply chain visibility and a dedicated customer service team
        at your service to answer any questions you might have</li>
      </ul>
    </div>
    <br />
    <br />

    <br/>
    <button class="button" @click="$router.push({name: 'parts', params: { id: 'abc123' },})">Click here to make your order now!</button>
    <br /><br />
  </div>
</template>
  <div>
  </div>

<style>
.textParts {
  float: right;
  width: 50%;
  padding: 5px;
  font-family: "Georgia", "serif"
}

.subtitle {
  font-family: "Georgia", "serif";
  text-align: center;
  font-weight: 500;
  text-decoration: underline;
  padding-top: 200px;
  padding-bottom: 50px;

}

.textImage::after {
  content: "";
  clear: both;
}

.mainText {
  font-weight: 400;
  text-align: center;
}

.title {
  text-align: center;
  font-family: Fantasy,Copperplate;
}
.title2 {
  text-align: center;
}
.banner::after {
  float: right;
  width: 50%;
  padding-top: 100px;
}
.text2 {
  font-size: 18px;
  font-family: "Georgia", "serif";
  float: right;
  width: 50%;
  padding: 5px;
  content: "";
  clear: both;
  text-align: center;
}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 25px;
  width: 100%;
}

.bullets {
  font-style: italic;
  padding-bottom: 10px;
  font-size: 18px;
  text-align: left;
  list-style-type: circle;
}

.t {

  padding-left: 40px;
  font-size: 22px;
  font-weight: 700;
  margin-top: 40px;
}
</style>
<script>
import UserService from '../services/user.service'
import image from '../assets/cars2.png'
import partsImage from '../assets/spareparts.png'
import bannerParts from '../assets/part-banner.jpg'
import homeParts from '../assets/home-banner.png'
export default {
  name: 'Home',
  data () {
    return {
      content: '',
      image: image,
      partsImage: partsImage,
      bannerParts: bannerParts,
      homeParts: homeParts
    }
  },
  mounted () {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString()
      }
    )
  }
}
</script>
