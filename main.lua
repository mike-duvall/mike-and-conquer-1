

function love.load()

   if arg[#arg] == "-debug" then require("mobdebug").start() end  
--- create a character table, our character is a rectangle, the initial position of the character object in the x and y coordinate is defined in the table as 300 and 400 respectively

  character = {}
  character.x = 300
  character.y = 400

  love.graphics.setBackgroundColor(225, 153, 0)

  -- paint character blue 

  love.graphics.setColor(0, 0, 225)

end

function love.draw()

    --- draw character
    love.graphics.rectangle("fill", character.x, character.y, 100, 100)
    
    
end

function love.update(dt)

--- On pressing the 'd' key, move to the right
    if love.keyboard.isDown('d') then
      -- the increment value can be changed depending on how far you want the object to go in single press of the button 
      character.x = character.x + 100 * dt

    --- else if we press the 'a' key, move to the left

   elseif love.keyboard.isDown('a') then

    character.x = character.x - 100 * dt
   end
--- if we press the 'W' key, move to the up
if love.keyboard.isDown('w') then

   character.y = character.y - 100 * dt

--- else if we press the 'S' key, move to the down

elseif love.keyboard.isDown('s') then

   character.y = character.y + 100 * dt
   end
   
end